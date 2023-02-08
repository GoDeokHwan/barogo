package io.test.barogo.domain.delivery.service;

import io.test.barogo.domain.accounts.controller.request.DeliveryAddressModifyRequest;
import io.test.barogo.domain.delivery.controller.request.DeliveryCreateRequest;
import io.test.barogo.domain.delivery.entity.dto.DeliveryDTO;
import io.test.barogo.domain.delivery.entity.dto.DeliveryWithAccountsDTO;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryService {
    DeliveryWithAccountsDTO create(DeliveryCreateRequest request);

    List<DeliveryDTO> search(Long accountsId, LocalDate startDate, LocalDate endDate);

    DeliveryDTO modifyAddress(Long accountsId, Long deliveryId, DeliveryAddressModifyRequest request);
}
