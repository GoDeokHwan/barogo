package io.test.barogo.domain.delivery.service;

import io.test.barogo.domain.delivery.controller.request.DeliveryCreateRequest;
import io.test.barogo.domain.delivery.entity.dto.DeliveryWithAccountsDTO;

public interface DeliveryService {
    DeliveryWithAccountsDTO create(DeliveryCreateRequest request);
}
