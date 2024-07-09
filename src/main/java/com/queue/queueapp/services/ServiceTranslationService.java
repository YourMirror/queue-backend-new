package com.queue.queueapp.services;

import com.queue.queueapp.dto.*;

import java.util.List;

public interface ServiceTranslationService {
    List<ServiceTranslationDTO> getServicesByLanguage(String languageCode);
    Long createService(CreateServiceDTO createServiceDTO);
    void addTranslation(AddTranslationDTO addTranslationDTO);
    void addLanguage(AddLanguageDTO addLanguageDTO);
    void deleteLanguage(String languageCode);
    void updateLanguage(UpdateLanguageDTO updateLanguageDTO);
    void deleteService(Long serviceId);
    void updateTranslation(UpdateTranslationDTO updateTranslationDTO);
}
