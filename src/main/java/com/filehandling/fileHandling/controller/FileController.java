package com.filehandling.fileHandling.controller;

import com.filehandling.fileHandling.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // ✅ Upload API
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {

        String response = fileService.uploadFile(file);
        return ResponseEntity.ok(response);
    }

    // ✅ Download API
    @GetMapping(
            value = "/download/{fileName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )    public ResponseEntity<Resource> downloadFile(
            @PathVariable String fileName) {

        Resource resource = fileService.downloadFile(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM) // force download
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}