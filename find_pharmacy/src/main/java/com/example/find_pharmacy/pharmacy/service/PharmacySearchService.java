package com.example.find_pharmacy.pharmacy.service;

import com.example.find_pharmacy.pharmacy.dto.PharmacyDto;
import com.example.find_pharmacy.pharmacy.entity.Pharmacy;
import com.example.find_pharmacy.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacySearchService {

    private final PharmacyRepository pharmacyRepository;

    public List<PharmacyDto> searchPharmacyDtoList() {
        return pharmacyRepository.findAll()
                    .stream()
                    .map(this::convertToPharmacyDto)
                    .collect(Collectors.toList());
    }

    private PharmacyDto convertToPharmacyDto(Pharmacy pharmacy) {
        return PharmacyDto.builder()
                .id(pharmacy.getId())
                .pharmacyAddress(pharmacy.getPharmacyAddress())
                .pharmacyName(pharmacy.getPharmacyName())
                .latitude(pharmacy.getLatitude())
                .longitude(pharmacy.getLongitude())
                .build();
    }
}
