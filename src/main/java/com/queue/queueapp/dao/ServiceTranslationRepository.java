package com.queue.queueapp.dao;

import com.queue.queueapp.model.ServiceTranslationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceTranslationRepository extends JpaRepository<ServiceTranslationModel, Long> {
    //List<ServiceTranslationModel> findByLanguageCode(String languageCode);

    @Query("SELECT st FROM ServiceTranslationModel st WHERE st.language.code = :languageCode OR (st.language.code = 'en' AND st.service.id NOT IN (SELECT st2.service.id FROM ServiceTranslationModel st2 WHERE st2.language.code = :languageCode))")
    List<ServiceTranslationModel> findByLanguageCodeOrFallbackToEnglish(@Param("languageCode") String languageCode);

}
