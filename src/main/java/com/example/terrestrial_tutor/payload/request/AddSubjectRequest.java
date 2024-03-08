package com.example.terrestrial_tutor.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddSubjectRequest {
    private String subject;
    private List<Long> ids;
}
