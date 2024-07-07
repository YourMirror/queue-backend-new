package com.queue.queueapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
public class QueueModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private UserModel user;

    private String description;

    private LocalDateTime time;

    private int duration;

    private String procedureName;

    private String userComment;

    @PrePersist
    @PreUpdate
    private void convertTimeToUTC() {
        this.time = ZonedDateTime.of(this.time, ZoneId.of("Asia/Jerusalem")).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
    }

    @PostLoad
    private void convertTimeToIsraelZone() {
        this.time = ZonedDateTime.of(this.time, ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Asia/Jerusalem")).toLocalDateTime();
    }
}
