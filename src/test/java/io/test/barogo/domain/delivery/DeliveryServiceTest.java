package io.test.barogo.domain.delivery;

import io.test.barogo.domain.delivery.controller.request.DeliveryCreateRequest;
import io.test.barogo.domain.delivery.entity.dto.DeliveryDTO;
import io.test.barogo.domain.delivery.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@Slf4j
public class DeliveryServiceTest {

    @Autowired
    private DeliveryService deliveryService;

    @Test
    void 배달_신청_계정없을때_오류() {
        DeliveryCreateRequest request = DeliveryCreateRequest.of(3L, "서울시 강남구");
        assertThrows(RuntimeException.class, () -> {
           deliveryService.create(request);
        });
    }

    @Test
    void 배달_신청() {
        DeliveryCreateRequest request = DeliveryCreateRequest.of(1L, "서울시 강남구");
        DeliveryDTO delivery = deliveryService.create(request);
        assertEquals(delivery.getAddress(), "서울시 강남구");
    }
}
