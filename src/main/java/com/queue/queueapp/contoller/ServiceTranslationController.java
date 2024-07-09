package com.queue.queueapp.contoller;

import com.queue.queueapp.dto.*;
import com.queue.queueapp.services.ServiceTranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServiceTranslationController {
    private final ServiceTranslationService serviceTranslationService;

    @GetMapping("/services")
    public ResponseEntity<List<ServiceTranslationDTO>> getServicesByLanguage(@RequestParam String languageCode) {
        List<ServiceTranslationDTO> services = serviceTranslationService.getServicesByLanguage(languageCode);
        return ResponseEntity.ok(services);
    }
    @PostMapping("/admin/services/create")
    public ResponseEntity<Long> createService(@RequestBody CreateServiceDTO createServiceDTO) {
        Long serviceId = serviceTranslationService.createService(createServiceDTO);
        return ResponseEntity.ok(serviceId);
    }

    @PostMapping("/admin/services/translation")
    public ResponseEntity<Void> addTranslation(@RequestBody AddTranslationDTO addTranslationDTO) {
        serviceTranslationService.addTranslation(addTranslationDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/languages")
    public ResponseEntity<Void> addLanguage(@RequestBody AddLanguageDTO addLanguageDTO) {
        serviceTranslationService.addLanguage(addLanguageDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/languages/{code}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable String code) {
        serviceTranslationService.deleteLanguage(code);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/languages")
    public ResponseEntity<Void> updateLanguage(@RequestBody UpdateLanguageDTO updateLanguageDTO) {
        serviceTranslationService.updateLanguage(updateLanguageDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/services/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceTranslationService.deleteService(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/services/translation")
    public ResponseEntity<Void> updateTranslation(@RequestBody UpdateTranslationDTO updateTranslationDTO) {
        serviceTranslationService.updateTranslation(updateTranslationDTO);
        return ResponseEntity.ok().build();
    }

}
