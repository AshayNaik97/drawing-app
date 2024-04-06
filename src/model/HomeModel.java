package model;
import java.io.Serializable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeModel implements Serializable{
    public List<String> getFolderList() {
        List<String> folderList = new ArrayList<>();
        File saveFolder = new File("./save"); // Assuming the save folder is in the parent directory
        if (saveFolder.exists() && saveFolder.isDirectory()) {
            for (File file : saveFolder.listFiles()) {
                if (file.isDirectory()) {
                    folderList.add(file.getName());
                }
            }
        }
        return folderList;
    }

    public String getImagesInFolder(String folderName) {
        String image = "";
        File folder = new File("./save/" + folderName);
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    image=file.getName();
                }
            }
        }
        return image;
    }
}

