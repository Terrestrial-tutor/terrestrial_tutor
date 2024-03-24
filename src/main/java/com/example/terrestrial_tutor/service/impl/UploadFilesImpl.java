package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.service.UploadFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UploadFilesImpl implements UploadFilesService {

    @Value("${upload.path}")
    private String uploadPath;

    public Set<String> uploadFiles(Set<MultipartFile> files) throws IOException {
        System.out.println(files.size());
        Set<String> filesList = new HashSet<>();
        for (MultipartFile file : files) {
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
                filesList.add(fileName);
            }
        }
        return filesList;
    }

}
