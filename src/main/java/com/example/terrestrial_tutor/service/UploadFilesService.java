package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.TaskEntity;
import com.example.terrestrial_tutor.payload.response.FilesResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public interface UploadFilesService {
    Set<String> uploadFiles(Set<MultipartFile> file, TaskEntity curTak) throws IOException;
    Set<FilesResponse> getFilesByPaths(Set<String> paths) throws IOException;
}
