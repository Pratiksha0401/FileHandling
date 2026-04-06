package com.filehandling.fileHandling.service.impl;

import com.filehandling.fileHandling.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    private final Path fileStorageLocation;

    public FileServiceImpl(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create upload directory");
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String fileName = Objects.requireNonNull(file.getOriginalFilename());

            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return "File uploaded successfully: " + fileName;

        } catch (IOException ex) {
            throw new RuntimeException("File upload failed", ex);
        }
    }

    @Override
    public Resource downloadFile(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found");
            }

        } catch (Exception ex) {
            throw new RuntimeException("File download failed", ex);
        }
    }
}
