package com.cms.controller;

import com.cms.common.JwtUtil;
import com.cms.common.Result;
import com.cms.entity.FileRecord;
import com.cms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public Result<FileRecord> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "clubId", required = false) Long clubId,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long uploaderId = jwtUtil.getUserIdFromToken(token);

            // Create upload directory if not exists
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null) {
                int dotIndex = originalFilename.lastIndexOf(".");
                if (dotIndex >= 0 && dotIndex < originalFilename.length()) {
                    extension = originalFilename.substring(dotIndex);
                }
            } else {
                originalFilename = UUID.randomUUID().toString();
            }
            String uniqueFilename = UUID.randomUUID().toString() + extension;
            Path filePath = uploadPath.resolve(uniqueFilename);

            // Save file
            file.transferTo(filePath.toFile());

            // Save file record
            FileRecord fileRecord = new FileRecord();
            fileRecord.setUploaderId(uploaderId);
            fileRecord.setClubId(clubId);
            fileRecord.setFilename(originalFilename);
            fileRecord.setFilePath(filePath.toString());
            fileRecord.setFileType(file.getContentType());

            fileService.saveFileRecord(fileRecord);
            return Result.success(fileRecord);
        } catch (IOException e) {
            return Result.error(500, "File upload failed: " + e.getMessage());
        }
    }

    @GetMapping
    public Result<List<FileRecord>> getAllFiles() {
        return Result.success(fileService.getAllFiles());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        try {
            FileRecord fileRecord = fileService.getFileById(id);
            if (fileRecord == null) {
                return ResponseEntity.notFound().build();
            }

            File file = new File(fileRecord.getFilePath());
            Resource resource = new FileSystemResource(file);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + fileRecord.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteFile(@PathVariable Long id) {
        try {
            FileRecord fileRecord = fileService.getFileById(id);
            if (fileRecord != null) {
                // Delete physical file
                File file = new File(fileRecord.getFilePath());
                if (file.exists()) {
                    file.delete();
                }
            }
            fileService.deleteFile(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(500, "File deletion failed");
        }
    }
}
