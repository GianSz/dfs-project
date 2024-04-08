package com.client.infrastructure.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
@Slf4j
public class FileManager {

    public File getFilesFolder() throws IOException {
        String tempDirPath = System.getProperty("java.io.tmpdir");
        Path filesPath = Files.createDirectories(Paths.get(tempDirPath, "files"));

        return filesPath.toFile();
    }

    public String saveFileToUploadLocally(File filesDir, String fileName,byte[] fileBytes){
        String path = "";
        try {
            File newFile = new File(filesDir, fileName);
            if(newFile.createNewFile()){
                path = newFile.getAbsolutePath();
                Files.write(Path.of(path), fileBytes, StandardOpenOption.WRITE);
            }else{
                throw new IOException("Filename already exist");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }

    public String splitFileBySize(String sourceFilePath, String destinationPath, int chunkSizeInKB) throws IOException {
        File sourceFile = new File(sourceFilePath);
        int chunkSizeInBytes = chunkSizeInKB * 1024; // Convert KB to bytes
        byte[] buffer = new byte[chunkSizeInBytes];
        int bytesRead;
        String fileName = sourceFile.getName().split("\\.")[0];
        int chunkCounter = 0;

        try (InputStream inputStream = new FileInputStream(sourceFile)) {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String chunkFileName = fileName + "_" + chunkCounter + "." + sourceFile.getName().split("\\.")[1];
                File chunkFile = new File(destinationPath, chunkFileName);
                try (OutputStream outputStream = new FileOutputStream(chunkFile)) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                chunkCounter++;
            }
        }
        if(sourceFile.delete()){
            log.info("Original File deleted");
        }else{
            log.error("Error deleting file");
        }

        return destinationPath;
    }

}
