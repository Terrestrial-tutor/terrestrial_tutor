package com.example.terrestrial_tutor.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UploadFilesService {
    String uploadFiles(MultipartFile file) throws IOException;
}
