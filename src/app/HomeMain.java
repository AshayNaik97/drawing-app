package app;

import controller.HomeController;
import model.HomeModel;
import model.ModelUser;

import java.io.File;
import views.HomeView;
import java.util.ArrayList;
import java.util.List;
import model.ModelUser;

public class HomeMain {
    public static void main(ModelUser user) {
            HomeModel model = new HomeModel(user);
            // HomeView Homeview = new HomeView(model,user);
            HomeController controller = new HomeController(model,user);
     
    }
}

