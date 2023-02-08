package io.test.barogo.domain.delivery;

import io.test.barogo.domain.accounts.controller.request.DeliveryAddressModifyRequest;
import io.test.barogo.domain.delivery.controller.request.DeliveryCreateRequest;
import io.test.barogo.domain.delivery.entity.dto.DeliveryDTO;
import io.test.barogo.domain.delivery.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@Slf4j
public class DeliveryServiceTest {

    @Autowired
    private DeliveryService deliveryService;

    @Test
    @Description("배달 신청 계정없을때 오류 케이스")
    void 배달_신청_계정없을때_오류() {
        DeliveryCreateRequest request = DeliveryCreateRequest.of(3L, "서울시 강남구");
        assertThrows(RuntimeException.class, () -> {
           deliveryService.create(request);
        });
    }

    @Test
    @Description("배달 신청")
    void 배달_신청() {
        DeliveryCreateRequest request = DeliveryCreateRequest.of(1L, "서울시 강남구");
        DeliveryDTO delivery = deliveryService.create(request);
        assertEquals(delivery.getAddress(), "서울시 강남구");
    }

    @Test
    @Description("계정에 해당하는 배달조회")
    void 계정에_해당_하는_배달_조회() {
        List<DeliveryDTO> deliveryList = deliveryService.search(1L, LocalDate.of(2023, 2,7), LocalDate.of(2023, 2,8));
        assertEquals(2, deliveryList.size());
    }

    @Test
    @Description("배달 주소 변경 실패")
    void 배달_주소_변경_실패() {
        // 대기중이 아닐때 실패
        assertThrows(RuntimeException.class, () -> {
            deliveryService.modifyAddress(1L, 1L, DeliveryAddressModifyRequest.of("경기도 수원시"));
        });
        // 없은 배달
        assertThrows(RuntimeException.class, () -> {
            deliveryService.modifyAddress(1L, 6L, DeliveryAddressModifyRequest.of("경기도 수원시"));
        });
    }

    @Test
    @Description("배달 주소 변경")
    void 배달_주소_변경() {
        DeliveryDTO delivery = deliveryService.modifyAddress(1L, 3L, DeliveryAddressModifyRequest.of("경기도 수원시"));
        assertEquals(delivery.getAddress(), "경기도 수원시");
    }
}
