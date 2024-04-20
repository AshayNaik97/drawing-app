package main;

import controller.HomeController;
import model.HomeModel;
import model.ModelUser;
import view.HomeView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.ModelUser;

public class HomeMain {
    public static void main(ModelUser user,DrawingMain drawingmain) {
            HomeModel Homemodel = new HomeModel(user);
            HomeView Homeview = new HomeView(user);
            HomeController controller = new HomeController(drawingmain,Homemodel,Homeview,user);
            controller.init();
    }
}

