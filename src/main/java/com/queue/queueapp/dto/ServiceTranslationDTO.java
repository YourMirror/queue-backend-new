package com.queue.queueapp.dto;

import lombok.Data;

@Data
public class ServiceTranslationDTO {
    private Long id;
    private int durationInMinutes;
    private double price;
    private String name;
}
