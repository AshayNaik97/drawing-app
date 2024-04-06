package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.HomeModel;
import views.HomeView;
import app.DrawingApp;
import app.Main;

public class HomeController {
    private HomeModel model;
    private HomeView view;

    public HomeController(HomeModel model, HomeView view) {
        this.model = model;
        this.view = view;
        initView();
    }

    public void initView() {
        List<String> folders = model.getFolderList();
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
            DrawingApp da = new DrawingApp();
            if(Name == "New"){
                da.main(null);
            }
            else if(Name == "LogOut"){
                System.out.println("Logout");
                Main.main(null);

            }
            else{
                da.main(Name);
            }
            // this.dispose();
            view.exit();
        }
    }

}

