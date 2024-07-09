package com.queue.queueapp.dto;
import lombok.Data;

@Data
public class CreateServiceDTO {
    private int durationInMinutes;
    private double price;
    private String englishTranslationName;

}
