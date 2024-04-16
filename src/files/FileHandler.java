package files;

import java.util.List;
import java.util.Map;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.zip.*;

import connection.DatabaseConnection;

import java.nio.file.attribute.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.SQLException;

public class FileHandler {
    private Connection conn;

    public FileHandler(Connection conn) {
        this.conn = conn;
    }

    public void deleteDrawing(String FileName,int UserID) {
        try {
            
            String deleteQuery = "DELETE FROM userfiles WHERE userid = ? AND filename = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, UserID);
            preparedStatement.setString(2, FileName);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            System.out.println("File deleted successfully.");

        } catch (SQLException  e) {
            e.printStackTrace();
        }
    }

    public void postDrawing(String directoryPath,String FileName,int UserID) {
        try {
            // Create a sample text file
            String FilePath = directoryPath;

            // Create a zip file from the text file
            String zipFilePath = directoryPath + "/" + FileName;
            // createZipFile(FilePath, zipFilePath);
            FilesUtils.createZipFile(FilePath, zipFilePath);

            // Read the zip file
            File file = new File(zipFilePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] zipData = new byte[(int) file.length()];
            fis.read(zipData);
            fis.close();

            // Insert zip file into the database
            String insertQuery = "INSERT INTO userfiles (userid,filename,file) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setInt(1, UserID);
            preparedStatement.setString(2, FileName);
            preparedStatement.setBinaryStream(3, new ByteArrayInputStream(zipData), zipData.length);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            FilesUtils.ZipFileDeleter(zipFilePath);
            System.out.println("File inserted successfully.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map.Entry<String, Date>> getListDrawings(int userId, Connection conn) {
        List<Map.Entry<String, Date>> drawings = new ArrayList<>();
        try {
            String selectQuery = "SELECT filename, last_modified FROM userfiles WHERE userid = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String filename = resultSet.getString("filename");
                Date lastModified = resultSet.getDate("last_modified");
                drawings.add(new AbstractMap.SimpleEntry<>(filename, lastModified));
            }
            return drawings;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public byte[] getDrawing(int UserId,String FileName) {
        
        try {
            byte[] zipData = null;
            String selectQuery = "SELECT file FROM userfiles WHERE userid = ? AND filename = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
            preparedStatement.setInt(1, UserId);
            preparedStatement.setString(2, FileName);
            ResultSet resultSet = preparedStatement.executeQuery();

            // If there's a result, unzip the file
            if (resultSet.next()) {
                zipData = resultSet.getBytes("file");
                System.out.println("File fetched successfully.");
            } else {
                System.out.println("No file found in the database.");
            }

            // Close the result set, prepared statement, and connection
            resultSet.close();
            preparedStatement.close();
            return zipData;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}




