package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.HomeModel;
import views.HomeView;
import app.DrawingApp;
import app.Main;
import model.ModelUser;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
public class HomeController {
    private HomeModel model;
    private HomeView view;
    private ModelUser user;

    public HomeController(HomeModel model, ModelUser user) {
        this.model = model;
        this.user = user;
        this.view = new HomeView(user);
        init();
    }

    public void init() {
        List<String> folders = model.getFolderList();
        Path path = Paths.get("./save/"+user.getUserName()+user.getEmail());

        if (!Files.exists(path)) { // Check if directory already exists
            try {
                Files.createDirectories(path); // Create directory and any nonexistent parent directories
                // System.out.println("Directory created successfully at: " + path);
            } catch (Exception e) {
                System.err.println("Failed to create directory: " + e.getMessage());
            }
        } else {
            // System.out.println("Directory already exists at: " + path);
        }
        view.addButton("LogOut",new ButtonListener("LogOut"));
        view.addButton("New",new ButtonListener("New"));


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
            DrawingApp drawingapp = new DrawingApp();
            if(Name == "New"){
                drawingapp.main(null,user);
            }
            else if(Name == "LogOut"){
                System.out.println("Logout");
                Main.main(null);

            }
            else{
                drawingapp.main(Name,user);
            }
            // this.dispose();
            view.exit();
        }
    }

}

