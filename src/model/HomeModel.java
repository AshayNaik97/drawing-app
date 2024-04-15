package model;
import java.io.Serializable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeModel implements Serializable{
    private ModelUser user;

    public HomeModel(ModelUser user){
        this.user = user;
    }

    public List<String> getFolderList() {
        List<String> folderList = new ArrayList<>();
        File saveFolder = new File("./save/"+user.getUserName()+user.getEmail()); 
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
        File folder = new File("./save/"+user.getUserName()+user.getEmail()+"/"+folderName);
        // System.out.println(folder);
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                // System.out.println(file);
                if (file.isFile() && file.getName().endsWith(".png")) {
                    image=file.getName();
                    // System.out.println(image);
                }
            }
        }
        return image;
    }
}

