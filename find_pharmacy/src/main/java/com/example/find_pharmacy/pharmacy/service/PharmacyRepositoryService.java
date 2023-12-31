package com.example.find_pharmacy.pharmacy.service;

import com.example.find_pharmacy.pharmacy.entity.Pharmacy;
import com.example.find_pharmacy.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacyRepositoryService {
    private final PharmacyRepository pharmacyRepository;

    @Transactional
    public void updateAddress(Long id, String address) {
        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if (Objects.isNull(entity)) {
            log.error("PharmacyRepositoryService updateAddress not found id: {}", id);
            return;
        }

        entity.changePharmacyAddress(address);
    }

    public void updateAddress1(Long id, String address) {
        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if (Objects.isNull(entity)) {
            log.error("PharmacyRepositoryService updateAddress1 not found id: {}", id);
            return;
        }

        entity.changePharmacyAddress(address);
    }
}
