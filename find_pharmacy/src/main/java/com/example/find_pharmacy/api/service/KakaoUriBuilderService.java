package com.example.find_pharmacy.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
public class KakaoUriBuilderService {
    private static final String KAKAO_LOCAL_SEARCH_ADDRESS_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    public URI buildUriByAddressSearch(String address) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCAL_SEARCH_ADDRESS_URL);
        uriComponentsBuilder.queryParam("query",address);

        URI uri = uriComponentsBuilder.build().encode().toUri();
        log.info("KakaoUriBuilderService URI address:{} uri:{}", address,uri);
        return uri;
    }
}
