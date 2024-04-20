package controller.files;

import java.io.*;
import java.nio.file.*;
import java.util.Comparator;
import java.util.zip.*;
import java.nio.file.attribute.*;
import java.io.IOException;
import java.util.Date;

public final class FilesUtils {

    private FilesUtils(){}
    
    public static void createZipFile(String sourceDir, String zipFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            Path sourcePath = Paths.get(sourceDir);
            Files.walk(sourcePath)
                 .filter(path -> !Files.isDirectory(path))
                 .forEach(path -> {
                     ZipEntry zipEntry = new ZipEntry(sourcePath.relativize(path).toString());
                     try {
                         zos.putNextEntry(zipEntry);
                         Files.copy(path, zos);
                         zos.closeEntry();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 });
        }
    }

    public static void unzip(byte[] zipData, String extractDir) throws IOException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(zipData);
                ZipInputStream zis = new ZipInputStream(bis)) {
            // File dir = new File(extractDir);
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String filePath = extractDir + File.separator + zipEntry.getName();
                if (!zipEntry.isDirectory()) {
                    extractFile(zis, filePath);
                } else {
                    File dirEntry = new File(filePath);
                    dirEntry.mkdirs();
                }
                zipEntry = zis.getNextEntry();
            }
        }
    }

    public static void extractFile(ZipInputStream zis, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = zis.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }

    }

    public static void DirectoryRemover(String FilePath) {
            Path directoryPath = Paths.get(FilePath);
            
            try {
                Files.walk(directoryPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
                System.out.println("Directory removed successfully.");
            } catch (IOException e) {
                System.err.println("Failed to remove directory: " + e.getMessage());
            }
    }

    public static void ZipFileDeleter(String FilePath){
            String zipFilePath = FilePath;
            File zipFile = new File(zipFilePath);

            if (zipFile.exists()) {
                if (zipFile.delete()) {
                    System.out.println("Zip file deleted successfully.");
                } else {
                    System.err.println("Failed to delete zip file.");
                }
            } else {
                System.err.println("Zip file does not exist.");
            }
    }

    public static boolean isFolderNewer(String filePath, Date DBDate) {
        Path folderPath = Paths.get(filePath);

        try {
            BasicFileAttributes attrs = Files.readAttributes(folderPath, BasicFileAttributes.class);
            FileTime lastModifiedTime = attrs.lastModifiedTime();
            Date folderModifiedDate = new Date(lastModifiedTime.toMillis());
            
            return folderModifiedDate.after(DBDate);
        } catch (IOException e) {
            System.err.println("Failed to retrieve last modified date and time: " + e.getMessage());
            return false;
        }
    }

}
