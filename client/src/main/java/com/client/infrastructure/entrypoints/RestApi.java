package com.client.infrastructure.entrypoints;

import com.client.infrastructure.drivenadapters.datanode.DataNodeClient;
import com.client.infrastructure.drivenadapters.namenode.NameNodeClient;
import com.client.infrastructure.helpers.FileManager;
import com.namenode.grpc.DataNodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApi {

    private final FileManager fileManager;
    private final NameNodeClient nameNodeClient;
    private final DataNodeClient dataNodeClient;

    @PostMapping("/upload")
    private ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File filesDir = fileManager.getFilesFolder();
            String savedFilePath = fileManager.saveFileToUploadLocally(filesDir, file.getOriginalFilename(), file.getBytes());
            log.info("File size: {}", file.getSize());
            String splitedFiles = fileManager.splitFileBySize(savedFilePath, filesDir.getAbsolutePath(), 15);
            log.info("Splited files at: {}", splitedFiles);
            String fileExtension = file.getOriginalFilename().split("\\.")[1];
            Map<String, DataNodes> blocksDistribution = nameNodeClient.upload(file.getOriginalFilename().split("\\.")[0], file.getSize());
            blocksDistribution.forEach((blockName, dataNode) -> {
                dataNodeClient.upload(blockName + "." +fileExtension);
            });
        }
        catch (IOException e){
            return ResponseEntity.internalServerError().body("Cannot access to files folder!");
        }

        return ResponseEntity.ok("Uploaded file " + file.getOriginalFilename() );
    }

    @GetMapping("/download")
    private ResponseEntity<byte[]> downloadFile(@RequestParam("fileName") String fileName) {
        return ResponseEntity.ok(new byte[2]);
    }

}
