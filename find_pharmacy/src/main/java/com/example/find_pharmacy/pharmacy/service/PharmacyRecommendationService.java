package com.example.find_pharmacy.pharmacy.service;

import com.example.find_pharmacy.api.dto.DocumentDto;
import com.example.find_pharmacy.api.dto.KakaoApiResponseDto;
import com.example.find_pharmacy.api.service.KakaoAddressSearchService;
import com.example.find_pharmacy.direction.entity.Direction;
import com.example.find_pharmacy.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacyRecommendationService {
    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendPharmacyList(String address) {
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentDtoList())) {
            log.error("추천할 수가 없어! address: {}",address);
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentDtoList().get(0);
        List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto);
        //List<Direction> directionList = directionService.buildDirectionList(documentDto);
        directionService.saveAll(directionList);
    }
}
