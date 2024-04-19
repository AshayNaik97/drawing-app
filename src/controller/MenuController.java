package controller;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.DrawingApp;
import connection.DatabaseConnection;

import java.awt.image.BufferedImage;
import files.AssetLoader;
import files.FileLoader;
import files.LogFileLoader;
import files.SerializableFileLoader;
import frame.DrawingFrame;
import model.DrawingModel;
import shapes.Command;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import files.FileHandler;
import model.ModelUser;

public class MenuController implements Serializable {
	private DrawingModel model;
	private DrawingFrame frame;
	private String currentDir = DrawingApp.currentDir;
	private ModelUser usr= DrawingApp.usr;
	private FileHandler fd = new FileHandler(DatabaseConnection.getInstance().getConnection());
	private Stack<Command> undoStack=new Stack<Command>();
	private Stack<Command> redoStack=new Stack<Command>();
	
	private Stack<String> undoStackLog=new Stack<String>();
	private Stack<String> redoStackLog=new Stack<String>();
	private int saveNeeded;
	public MenuController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		System.out.println("assign");
		this.saveNeeded = model.getAll().size();
	}
	public void setSave(){
		this.saveNeeded = getUndoStack().size();
	}
	public boolean isSaveNeeded(){
		if(saveNeeded !=getUndoStack().size()){
			return true;
		}
		return false;

	}
	public void clearDrawing(ActionEvent e) {
		if(isSaveNeeded()){
			saveFiles();
		}
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this drawing?","Warning",JOptionPane.YES_NO_OPTION);
		if(dialogResult == JOptionPane.YES_OPTION) {
			if(frame.getToolsController().isEnterSelecting())
				frame.getToolsController().selectShape(null);
			model.getAll().clear();
			getUndoStack().clear();
			getRedoStack().clear();
			frame.getLogView().getLogPane().setText("");
			frame.getToolsController().updateButtons();
			frame.getView().repaint();
			}
	}

	public void saveFiles() {
		// lastSaveDateTime =  LocalDateTime.now();
		JFileChooser fc = new JFileChooser();
        String fileName = JOptionPane.showInputDialog(frame, "Enter file name:", "Save", JOptionPane.INFORMATION_MESSAGE);

        if (fileName != null && !fileName.isEmpty()) {
			Path directory = Paths.get(currentDir); // Project directory
			Path folderPath = directory.resolve(fileName);
			try {
				Files.createDirectories(folderPath);
			} catch (IOException ex) {
				ex.printStackTrace();
				// return; // Exit if directory creation fails
			}
            File f = new File(folderPath.toString(), fileName +".png");
            BufferedImage im = makePanel(frame.getView());
            try {
                ImageIO.write(im, "png", f);
            } catch (Exception execp) {
                System.err.println(execp);
            }

            AssetLoader fileLoad;
            if (fc.getFileFilter().getDescription().equals("serialized file (.ser)")) {
                fileLoad = new SerializableFileLoader(model, frame);
            } else {
                fileLoad = new LogFileLoader(model, frame);
            }

            FileLoader fileLoader = new FileLoader(fileLoad);
			File f1 = new File(folderPath.toString(), fileName);
            fileLoader.saveFile(f1); // Save the file to project directory
			fd.deleteDrawing(fileName,usr.getUserID());
			fd.postDrawing(currentDir+"/"+fileName,fileName,usr.getUserID());
        }
    }

	public void saveFiles(ActionEvent e){
		saveFiles();
	}


	private BufferedImage makePanel(JPanel panel)
	{
	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.print(g);
	    return bi;
	}

	public void openFiles(ActionEvent e)  {
		if(isSaveNeeded()){
			saveFiles();
		}

		System.out.println(currentDir);
		JFileChooser f = new JFileChooser(new File(currentDir));
        f.setFileSelectionMode(JFileChooser.FILES_ONLY); 
        f.setFileFilter(new FileNameExtensionFilter("serialized file (.ser)", "ser"));
        f.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
        f.setDialogTitle("Save log file");
        int retrival =f.showOpenDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
        	AssetLoader fileLoad;
        	if(f.getFileFilter().getDescription().equals("serialized file (.ser)")){
        		fileLoad=new SerializableFileLoader(model,frame);
        	}else {
        		fileLoad=new LogFileLoader(model,frame);
        	}
        	
        	FileLoader fileLoader=new FileLoader(fileLoad);
        	fileLoader.loadFile(f.getSelectedFile());
        	frame.getView().repaint();
        
        }
	}
	
	public Stack<Command> getUndoStack(){
		// if(!undoStack.empty())
		// 	System.out.println("getUndoStack: "+undoStack.peek()+" ||| size:"+undoStack.size());
		return undoStack;
	}
	public Stack<Command> getRedoStack(){
		// if(!redoStack.empty())
		// 	System.out.println("getRedoStack: "+redoStack.peek()+" ||| size:"+redoStack.size());
		return redoStack;
	}

	public Stack<String> getUndoStackLog() {
		// if(!undoStackLog.empty())
		// 	System.out.println("getUndoStackLog: "+undoStackLog.peek()+" ||| size:"+undoStackLog.size());
		return undoStackLog;
	}

	public void setUndoStackLog(Stack<String> undoStackLog) {
		// System.out.println("setUndoStackLog: "+undoStackLog.peek()+" ||| size:"+undoStackLog.size());
		this.undoStackLog = undoStackLog;
	}

	public Stack<String> getRedoStackLog() {
		// if(!redoStackLog.empty())
			// System.out.println("getRedoStackLog: "+redoStackLog.peek()+" ||| size:"+redoStackLog.size());
		return redoStackLog;
	}

	public void setRedoStackLog(Stack<String> redoStackLog) {
		this.redoStackLog = redoStackLog;
		// System.out.println("setRedoStackLog: "+redoStackLog.peek()+" ||| size:"+redoStackLog.size());
	}
}
