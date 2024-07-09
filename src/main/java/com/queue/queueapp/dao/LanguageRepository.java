package com.queue.queueapp.dao;

import com.queue.queueapp.model.LanguageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<LanguageModel, Long> {
    Optional<LanguageModel> findByCode(String code);

}

