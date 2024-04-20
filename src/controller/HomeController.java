package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import model.HomeModel;
import connection.DatabaseConnection;
import controller.files.DBFileHandler;
import controller.files.FilesUtils;
import main.DrawingMain;
import main.Main;
import model.ModelUser;
import view.HomeView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Date;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class HomeController {
    private HomeModel model;
    private HomeView view;
    private ModelUser user;
    private Connection con;
    private DBFileHandler FileHandler;
    private DrawingMain drawingmain;

    public HomeController(DrawingMain drawingmain,HomeModel model,HomeView view, ModelUser user) {
        this.model = model;
        this.user = user;
        this.view = view;
        this.drawingmain = drawingmain;
        this.con = DatabaseConnection.getInstance().getConnection();
        FileHandler = new DBFileHandler(this.con);
        
    }

    public void init() {
        
        // create dir if not exist
        String curDir = "./save/"+user.getUserName()+user.getEmail();
        Path path = Paths.get(curDir);
        boolean newDirCreated = false;
        if (!Files.exists(path)) { // Check if directory already exists
            try {
                Files.createDirectories(path); // Create directory and any nonexistent parent directories
                newDirCreated = true;
                // System.out.println("Directory created successfully at: " + path);
            } catch (Exception e) {
                System.err.println("Failed to create directory: " + e.getMessage());
            }
        } else {
            // System.out.println("Directory already exists at: " + path);
        }
        // get or update files
        List<Map.Entry<String, Date>> drawings =FileHandler.getListDrawings(user.getUserID(),con);
         if(newDirCreated){
             //download and extract eveyfile from db
            for(Map.Entry<String, Date> drawing : drawings){
                try{
                    String drawingPathString = curDir+"/"+drawing.getKey();
                    Path drawingPath =Paths.get(drawingPathString);
                    Files.createDirectories(drawingPath);
                    FilesUtils.unzip(FileHandler.getDrawing(user.getUserID(), drawing.getKey()),drawingPathString);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            
        }
        else{
            //check all files and download and extract needed
            // FilesUtils.isFolderNewer(curDir+filename,)
            List<String> folderList = new ArrayList<>();
            File saveFolder = new File(curDir); 
            if (saveFolder.exists() && saveFolder.isDirectory()) {
                for (File file : saveFolder.listFiles()) {
                    if (file.isDirectory()) {
                        folderList.add(file.getName());
                    }
                }
            }
            for (String drawing : folderList) {
                boolean found = false;
                for (Map.Entry<String, Date> entry : drawings) {
                    if (entry.getKey().equals(drawing)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    FileHandler.postDrawing(curDir+"/"+drawing, drawing, user.getUserID());
                }
            }

            for(Map.Entry<String, Date> drawing : drawings){
                String drawingPathString = curDir+"/"+drawing.getKey();
                if(!folderList.contains(drawing.getKey())){
                    System.out.println("file dont contain");
                    try{
                        Path drawingPath =Paths.get(drawingPathString);
                        Files.createDirectories(drawingPath);
                        FilesUtils.unzip(FileHandler.getDrawing(user.getUserID(), drawing.getKey()),drawingPathString);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else{
                    if(!FilesUtils.isFolderNewer(drawingPathString, drawing.getValue())){
                        System.out.println("file contain but older");
                        FilesUtils.DirectoryRemover(drawingPathString);
                        try{
                            Path drawingPath =Paths.get(drawingPathString);
                            Files.createDirectories(drawingPath);
                            FilesUtils.unzip(FileHandler.getDrawing(user.getUserID(), drawing.getKey()),drawingPathString);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        
        
        List<String> folders = model.getFolderList();
        view.addLogoutButton("LogOut",new ButtonListener("LogOut"));
        view.addButton("New",new ImageIcon("images/icons8-plus-100.png"),new ButtonListener("New"));


        for (String folder : folders) {
            String image = model.getImagesInFolder(folder);
            view.addDrawing(folder, image, new ButtonListener(folder));
        }
        view.show();
    }

    // ActionListener for buttons
    private class ButtonListener implements ActionListener {
        private String Name;

        public ButtonListener(String Name) {
            this.Name = Name;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // System.out.println("Button clicked " + Name);
            if(Name == "New"){
                drawingmain.main(null,user);
            }
            else if(Name == "LogOut"){
                System.out.println("Logout");
                Main.main(null);

            }
            else{
                drawingmain.main(Name,user);
            }
            // this.dispose();
            view.exit();
        }
    }

}

