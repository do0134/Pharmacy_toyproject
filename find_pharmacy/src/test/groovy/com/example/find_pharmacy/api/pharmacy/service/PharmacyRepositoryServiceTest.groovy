package com.example.find_pharmacy.api.pharmacy.service

import com.example.find_pharmacy.api.AbstractIntegrationContainerBaseTest
import com.example.find_pharmacy.pharmacy.entity.Pharmacy
import com.example.find_pharmacy.pharmacy.repository.PharmacyRepository
import com.example.find_pharmacy.pharmacy.service.PharmacyRepositoryService
import org.springframework.beans.factory.annotation.Autowired

class PharmacyRepositoryServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PharmacyRepositoryService pharmacyRepositoryService

    @Autowired
    private PharmacyRepository pharmacyRepository

    def setup() {
        pharmacyRepository.deleteAll()
    }

    def "PharmacyRepository update - dirty checking success" () {
        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
        .pharmacyAddress(inputAddress)
        .pharmacyName(name)
        .build()

        when:
        def entity = pharmacyRepository.save(pharmacy)
        System.println("--------------------------------------------")
        pharmacyRepositoryService.updateAddress(entity.getId(), modifiedAddress)
        System.println("--------------------------------------------")
        def result = pharmacyRepository.findAll()
        System.println("--------------------------------------------")

        then:
        result.get(0).getPharmacyAddress() == modifiedAddress
    }

    def "PharmacyRepository update - dirty checking failed" () {
        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(inputAddress)
                .pharmacyName(name)
                .build()

        when:
        def entity = pharmacyRepository.save(pharmacy)
        System.println("--------------------------------------------")
        pharmacyRepositoryService.updateAddress1(entity.getId(), modifiedAddress)
        System.println("--------------------------------------------")
        def result = pharmacyRepository.findAll()
        System.println("--------------------------------------------")

        then:
        result.get(0).getPharmacyAddress() == inputAddress
    }
}
