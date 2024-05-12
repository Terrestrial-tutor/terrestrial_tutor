package com.example.terrestrial_tutor.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilesResponse {
    private String fileName;
    private byte[] file;
}
