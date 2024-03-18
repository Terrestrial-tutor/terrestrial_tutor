package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.service.UploadFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadFilesImpl implements UploadFilesService {

    @Value("${upload.path}")
    private String uploadPath;

    public String uploadFiles(MultipartFile file) throws IOException {
        if (file != null) {
            File uploadDir;
            if (file.getOriginalFilename().endsWith(".jpg") || file.getOriginalFilename().endsWith(".png")) {
                uploadDir = new File(uploadPath + "/images");
            } else if (file.getOriginalFilename().endsWith(".pdf")) {
                uploadDir = new File(uploadPath + "/pdfs");
            } else {
                uploadDir = new File(uploadPath + "/other_files");
            }

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String fileUuid = UUID.randomUUID().toString();
            String fileName = uploadDir + "/" + fileUuid + "." + file.getOriginalFilename();

            file.transferTo(new File(fileName).toPath());
            return fileName;
        }
        return "Uploading failed";
    }

}
