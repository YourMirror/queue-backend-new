package com.queue.queueapp.services;

import com.queue.queueapp.dao.LanguageRepository;
import com.queue.queueapp.dao.ServiceRepository;
import com.queue.queueapp.dao.ServiceTranslationRepository;
import com.queue.queueapp.dto.*;
import com.queue.queueapp.model.LanguageModel;
import com.queue.queueapp.model.ServiceModel;
import com.queue.queueapp.model.ServiceTranslationModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceTranslationServiceImpl implements ServiceTranslationService{
    private final ServiceTranslationRepository serviceTranslationRepository;
    private final ServiceRepository serviceRepository;
    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ServiceTranslationDTO> getServicesByLanguage(String languageCode) {

        List<ServiceTranslationModel> translations = serviceTranslationRepository.findByLanguageCodeOrFallbackToEnglish(languageCode);
        return translations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long createService(CreateServiceDTO createServiceDTO) {
        ServiceModel service = new ServiceModel();
        service.setDurationInMinutes(createServiceDTO.getDurationInMinutes());
        service.setPrice(createServiceDTO.getPrice());
        service = serviceRepository.save(service);
        // Add translation
        Optional<LanguageModel> englishLanguage = languageRepository.findByCode("en");
        if (englishLanguage.isPresent()) {
            ServiceTranslationModel translation = new ServiceTranslationModel();
            translation.setService(service);
            translation.setLanguage(englishLanguage.get());
            translation.setName(createServiceDTO.getEnglishTranslationName());
            serviceTranslationRepository.save(translation);
        } else {
            throw new IllegalArgumentException("English language not found");
        }
        return service.getId();
    }

    @Override
    public void addTranslation(AddTranslationDTO addTranslationDTO) {
        ServiceModel service = serviceRepository.findById(addTranslationDTO.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid service ID: " + addTranslationDTO.getServiceId()));
        LanguageModel language = languageRepository.findByCode(addTranslationDTO.getLanguageCode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid language code: " + addTranslationDTO.getLanguageCode()));

        ServiceTranslationModel translation = new ServiceTranslationModel(null, service, language, addTranslationDTO.getName());
        serviceTranslationRepository.save(translation);
    }

    @Override
    public void addLanguage(AddLanguageDTO addLanguageDTO) {
        LanguageModel language = new LanguageModel();
        language.setCode(addLanguageDTO.getCode());
        language.setName(addLanguageDTO.getName());
        languageRepository.save(language);
    }

    @Override
    public void deleteLanguage(String languageCode) {
        LanguageModel language = languageRepository.findByCode(languageCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid language code: " + languageCode));
        languageRepository.delete(language);
    }

    @Override
    public void updateLanguage(UpdateLanguageDTO updateLanguageDTO) {
        LanguageModel language = languageRepository.findByCode(updateLanguageDTO.getOldCode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid language code: " + updateLanguageDTO.getOldCode()));
        language.setCode(updateLanguageDTO.getNewCode());
        language.setName(updateLanguageDTO.getNewName());
        languageRepository.save(language);
    }

    @Override
    public void deleteService(Long serviceId) {
        ServiceModel service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid service ID: " + serviceId));
        serviceRepository.delete(service);
    }

    @Override
    public void updateTranslation(UpdateTranslationDTO updateTranslationDTO) {
        ServiceTranslationModel translation = serviceTranslationRepository.findById(updateTranslationDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid translation ID: " + updateTranslationDTO.getId()));
        translation.setName(updateTranslationDTO.getName());
        serviceTranslationRepository.save(translation);
    }

    private ServiceTranslationDTO convertToDTO(ServiceTranslationModel translation) {
        ServiceTranslationDTO dto = modelMapper.map(translation, ServiceTranslationDTO.class);
        dto.setId(translation.getService().getId());
        dto.setDurationInMinutes(translation.getService().getDurationInMinutes());
        dto.setPrice(translation.getService().getPrice());
        dto.setName(translation.getName());
        return dto;
    }
}
