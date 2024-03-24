package com.example.terrestrial_tutor.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;


public interface UploadFilesService {
    Set<String> uploadFiles(Set<MultipartFile> file) throws IOException;
}
