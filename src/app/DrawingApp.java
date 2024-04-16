package app;

import java.util.logging.Logger;
import java.io.File;
import javax.swing.JFrame;
import files.FileLoader;
import controller.DrawingController;
import controller.MenuController;
import controller.ToolsController;
import frame.DrawingFrame;
import logging.LogHandler;
import model.DrawingModel;
import model.ModelUser;
import files.AssetLoader;
import files.LogFileLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class DrawingApp {
	public static String currentDir;
	public static ModelUser usr;

	public static void main(String file,ModelUser user) {
		currentDir = "./save/"+user.getUserName()+user.getEmail();
		usr = user;
		AssetLoader fileLoad;	
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		DrawingController controller = new DrawingController(model, frame);
		ToolsController tController=new ToolsController(model, frame);
		MenuController mController=new MenuController(model, frame);
		Logger globalLogger = Logger.getLogger("global");

		frame.getView().setModel(model);
		frame.setController(controller);
		frame.setMenuController(mController);
		frame.setToolsController(tController);
		
		globalLogger.setUseParentHandlers(false);
		globalLogger.addHandler(new LogHandler(frame));
		frame.setSize(1200, 700);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(file != null){
			System.out.println("tryna load");
			Path directory = Paths.get(currentDir);
			Path folderPath = directory.resolve(file);
			File f = new File(folderPath.toString(), file +".log");
			System.out.println(f);
        	fileLoad=new LogFileLoader(model,frame);
			FileLoader fileLoader=new FileLoader(fileLoad);
        	fileLoader.loadFile(f);
        	frame.getView().repaint();
		}
	}

}
