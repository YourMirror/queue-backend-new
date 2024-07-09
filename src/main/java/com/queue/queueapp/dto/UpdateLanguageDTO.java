package com.queue.queueapp.dto;
import lombok.Data;

@Data
public class UpdateLanguageDTO {
    private String oldCode;
    private String newCode;
    private String newName;
}
