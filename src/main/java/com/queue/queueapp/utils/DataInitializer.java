package com.queue.queueapp.utils;

import com.queue.queueapp.model.LanguageModel;
import com.queue.queueapp.model.ServiceModel;
import com.queue.queueapp.model.ServiceTranslationModel;
import com.queue.queueapp.dao.LanguageRepository;
import com.queue.queueapp.dao.ServiceRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final LanguageRepository languageRepository;
    private final ServiceRepository serviceRepository;

    @PostConstruct
    public void init() {
        if (languageRepository.count() == 0) {
            LanguageModel english = new LanguageModel(null, "en", "English");
            LanguageModel russian = new LanguageModel(null, "ru", "Russian");
            LanguageModel hebrew = new LanguageModel(null, "he", "Hebrew");
            languageRepository.saveAll(Arrays.asList(english, russian, hebrew));
        }

        if (serviceRepository.count() == 0) {
            List<LanguageModel> languages = languageRepository.findAll();
            LanguageModel english = languages.stream().filter(lang -> lang.getCode().equals("en")).findFirst().orElseThrow();
            LanguageModel russian = languages.stream().filter(lang -> lang.getCode().equals("ru")).findFirst().orElseThrow();
            LanguageModel hebrew = languages.stream().filter(lang -> lang.getCode().equals("he")).findFirst().orElseThrow();

            addServiceWithTranslations("Manicure", "Маникюр", "מניקור", 60, 100.0, english, russian, hebrew);
            addServiceWithTranslations("Pedicure", "Педикюр", "פדיקור", 45, 80.0, english, russian, hebrew);
            addServiceWithTranslations("Haircut", "Стрижка", "תספורת", 30, 50.0, english, russian, hebrew);
        }
    }

    private void addServiceWithTranslations(String nameEn, String nameRu, String nameHe, int duration, double price,
                                            LanguageModel english, LanguageModel russian, LanguageModel hebrew) {
        ServiceModel service = new ServiceModel();
        service.setDurationInMinutes(duration);
        service.setPrice(price);
        ServiceTranslationModel translationEn = new ServiceTranslationModel(null, service, english, nameEn);
        ServiceTranslationModel translationRu = new ServiceTranslationModel(null, service, russian, nameRu);
        ServiceTranslationModel translationHe = new ServiceTranslationModel(null, service, hebrew, nameHe);
        service.setTranslations(Arrays.asList(translationEn, translationRu, translationHe));

        serviceRepository.save(service);
    }
}

