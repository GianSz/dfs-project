package com.client.infrastructure.entrypoints;

import com.client.infrastructure.drivenadapters.datanode.DataNodeClient;
import com.client.infrastructure.drivenadapters.namenode.NameNodeClient;
import com.client.infrastructure.helpers.FileManager;
import com.namenode.grpc.DataNodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApi {

    private final FileManager fileManager;
    private final NameNodeClient nameNodeClient;
    private final DataNodeClient dataNodeClient;

    @Value("${files.chunckSize}")
    private Integer chunckSize;

    @PostMapping("/upload")
    private ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File filesDir = fileManager.getFilesFolder();
            String savedFilePath = fileManager.saveFileToUploadLocally(filesDir, file.getOriginalFilename(), file.getBytes());
            log.info("File size: {}", file.getSize());
            String splitedFiles = fileManager.splitFileBySize(savedFilePath, filesDir.getAbsolutePath(), chunckSize);
            log.info("Splited files at: {}", splitedFiles);
            Map<String, DataNodes> blocksDistribution = new HashMap<>();
            try{
                blocksDistribution = nameNodeClient.upload(file.getOriginalFilename(), file.getSize());
            }catch (RuntimeException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            blocksDistribution.forEach((blockName, dataNodes) -> {
                dataNodes.getDataNodesList().forEach(dataNode -> {
                    log.info("Uploading {} to {}", blockName, dataNode);
                    dataNodeClient.upload(blockName, dataNode);
                });
            });
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Cannot access to files folder!");
        }

        return ResponseEntity.ok("Uploaded file " + file.getOriginalFilename());
    }

    @GetMapping("/download")
    private ResponseEntity downloadFile(@RequestParam("file") String file) {
        Map<String, DataNodes> blocksDistribution = new HashMap<>();
        try {
            blocksDistribution = nameNodeClient.download(file);
        }catch (RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
        ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
        blocksDistribution.forEach((block, dataNodes) -> {
            log.info("Downloading {} from {}", block, dataNodes.getDataNodes(0));
            try {
                responseBytes.write(dataNodeClient.download(block, dataNodes.getDataNodes(0)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseEntity.ok(responseBytes.toByteArray());
    }

}
