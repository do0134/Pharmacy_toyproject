package com.example.find_pharmacy.direction.controller;

import com.example.find_pharmacy.direction.entity.Direction;
import com.example.find_pharmacy.direction.repository.DirectionRepository;
import com.example.find_pharmacy.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService directionService;

    /**
     * 약국명, 위도, 경도를 콤마로 구분해서
     */
    private static final String DIRECTION_BASE_URL = "https://map.kakao.com/link/map/";

    @GetMapping("/dir/{encodedId}")
    public String searchDirection(@PathVariable("encodedId") String encodedId) {
        Direction direction = directionService.findById(encodedId);

        String parmas = String.join(",",direction.getTargetPharmacyName(),
                String.valueOf(direction.getTargetLatitude()),
                String.valueOf(direction.getTargetLongitude()));

        String result = UriComponentsBuilder.fromHttpUrl(DIRECTION_BASE_URL + parmas).toUriString();

        log.info("direction parmas: {}, uri: {}", parmas, result);

        return "redirect:" + result;
    }
}
