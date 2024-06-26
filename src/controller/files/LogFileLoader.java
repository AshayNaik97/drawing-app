package controller.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import model.DrawingModel;
import model.frame.DrawingFrame;
import model.logging.CommandTransfer;
import model.shapes.Command;
import model.shapes.Shape;
import view.LogParser;

public class LogFileLoader implements AssetLoader {
	private DrawingModel model;
	private DrawingFrame frame;
	public LogFileLoader(DrawingModel model,DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}
	@Override
	public void loadFile(File file) {
		 File selectedFile = file;
         if(selectedFile.getName().split("\\.")[1].equals("log"))
         try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
             String line,text="";
        	 LogParser lp=new LogParser();
             while ((line = br.readLine()) != null) {
            	 text=text+line+'\n';
            	 lp.getTpLogPreview().setText(text);
				//   lp.setConf(false);
				 lp.setConf(true); //updated
            	//  lp.setVisible(true);
            	 if(lp.isConf()) {
            		 CommandTransfer ct=new CommandTransfer(model,frame);
            		 Command c=ct.toCommand(line);
            		 frame.getView().repaint();
            		 if(c==null) {lp.dispose();break;}
            	 }
				//  else {lp.dispose();break;}
            	 for (Shape s : model.getAll()) {
					if(s.isSelected()) {
						if(!frame.getToolsController().isEnterSelecting())
						frame.getToolsController().selectShape(null);
						break;}
            	 }
             }
         }catch (Exception e2) {
				e2.printStackTrace();
         	JOptionPane.showMessageDialog(frame,
             	    "Error while reading the file.");
			}
         else JOptionPane.showMessageDialog(frame,
         	    "Wrong file format, file extension must be '.log'.");
	}


	@Override
	public void saveFile(File file) {
		try {
			FileWriter fw = new FileWriter(file+".log");
			fw.write(frame.getLogView().getLogPane().getText());
			fw.close();
            JOptionPane.showMessageDialog(null,
            	    "The file was saved successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
            	    "Error while saving the file.");
        }
	}

}
