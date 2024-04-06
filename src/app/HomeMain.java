package app;

import controller.HomeController;
import model.HomeModel;
import model.ModelUser;

import java.io.File;
import views.HomeView;
import java.util.ArrayList;
import java.util.List;

public class HomeMain {
    public static void main(String user) {
            HomeModel model = new HomeModel();
            HomeView Homeview = new HomeView(model,user);
            HomeController controller = new HomeController(model,Homeview);
     
    }
}

