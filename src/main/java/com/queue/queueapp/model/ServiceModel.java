package com.queue.queueapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int durationInMinutes;
    private double price;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<ServiceTranslationModel> translations;
}
