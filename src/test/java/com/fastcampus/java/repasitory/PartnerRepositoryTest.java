package com.fastcampus.java.repasitory;

import com.fastcampus.java.JavaApplicationTests;
import com.fastcampus.java.model.entity.Partner;
import com.fastcampus.java.model.enumclass.PartnerStatus;
import com.fastcampus.java.repository.PartnerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PartnerRepositoryTest extends JavaApplicationTests {
    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {
        String name = "Partner01";
        PartnerStatus status = PartnerStatus.REGISTERED;
        String address = "서울시 강남구";
        String callCenter = "070-1111-2222";
        String partnerNumber = "010-1111-2222";
        String businessNumber = "12345678";
        String ceoName = "홍길동";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Long categoryId = 4L;

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
        //partner.setCategoryId(categoryId);

        Partner newPartner = partnerRepository.save(partner);
        Assert.assertNotNull(newPartner);
        Assert.assertEquals(newPartner.getName(), name);
    }
}
