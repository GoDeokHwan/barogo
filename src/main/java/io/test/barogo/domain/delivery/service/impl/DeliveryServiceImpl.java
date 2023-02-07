package io.test.barogo.domain.delivery.service.impl;

import io.test.barogo.domain.accounts.entity.Accounts;
import io.test.barogo.domain.accounts.repository.AccountsRepository;
import io.test.barogo.domain.delivery.controller.request.DeliveryCreateRequest;
import io.test.barogo.domain.delivery.entity.Delivery;
import io.test.barogo.domain.delivery.entity.dto.DeliveryWithAccountsDTO;
import io.test.barogo.domain.delivery.repository.DeliveryRepository;
import io.test.barogo.domain.delivery.service.DeliveryService;
import io.test.barogo.support.ErrorCode;
import io.test.barogo.support.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final AccountsRepository accountsRepository;

    @Override
    public DeliveryWithAccountsDTO create(DeliveryCreateRequest request) {
        Accounts accounts = accountsRepository.findById(request.getAccountsId()).orElseThrow(() -> ErrorResponse.of(ErrorCode.NOT_FOUND_ACCOUNTS));

        return deliveryRepository.save(Delivery.ofCreate(accounts, request.getAddress())).toDeliveryWidhAccountsDTO();
    }
}
