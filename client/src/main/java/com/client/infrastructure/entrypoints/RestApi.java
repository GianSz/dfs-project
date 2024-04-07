package com.client.infrastructure.entrypoints;

import com.client.infrastructure.helpers.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApi {

    private final FileManager fileManager;

    @PostMapping("/upload")
    private ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File filesDir = fileManager.getFilesFolder();
            String savedFilePath = fileManager.saveFileToUploadLocally(filesDir, file.getOriginalFilename(), file.getBytes());
            log.info("File size: {}", file.getSize());
            String splitedFiles = fileManager.splitFileBySize(savedFilePath, filesDir.getAbsolutePath(), 15);
            log.info("Splited files at: {}", splitedFiles);
        }
        catch (IOException e){
            return ResponseEntity.internalServerError().body("Cannot access to files folder!");
        }

        return ResponseEntity.ok("Uploaded file " + file.getOriginalFilename());
    }

}
