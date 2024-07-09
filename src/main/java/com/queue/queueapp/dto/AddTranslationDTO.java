package com.queue.queueapp.dto;
import lombok.Data;

@Data
public class AddTranslationDTO {
    private Long serviceId;
    private String languageCode;
    private String name;
}
