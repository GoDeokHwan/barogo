package io.test.barogo.domain.delivery.service.impl;

import io.test.barogo.domain.accounts.entity.Accounts;
import io.test.barogo.domain.accounts.repository.AccountsRepository;
import io.test.barogo.domain.accounts.controller.request.DeliveryAddressModifyRequest;
import io.test.barogo.domain.delivery.controller.request.DeliveryCreateRequest;
import io.test.barogo.domain.delivery.entity.Delivery;
import io.test.barogo.domain.delivery.entity.dto.DeliveryDTO;
import io.test.barogo.domain.delivery.entity.dto.DeliveryWithAccountsDTO;
import io.test.barogo.domain.delivery.repository.DeliveryRepository;
import io.test.barogo.domain.delivery.service.DeliveryService;
import io.test.barogo.support.ErrorCode;
import io.test.barogo.support.ErrorResponse;
import io.test.barogo.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<DeliveryDTO> search(Long accountsId, LocalDate startDate, LocalDate endDate) {
        accountsRepository.findById(accountsId).orElseThrow(() -> ErrorResponse.of(ErrorCode.NOT_FOUND_ACCOUNTS));
        return deliveryRepository.findByAccountsIdAndRequestTimeBetween(accountsId, DateUtils.toStartLocalDateTime(startDate), DateUtils.toEndLocalDateTime(endDate))
                .stream()
                .map(Delivery::toDeliveryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryDTO modifyAddress(Long accountsId, Long deliveryId, DeliveryAddressModifyRequest request) {
        Delivery delivery = deliveryRepository.findByIdWithAccounts(deliveryId)
                .orElseThrow(() -> ErrorResponse.of(ErrorCode.BAD_REQUEST));

        if (delivery.getAccounts() == null || !delivery.getAccounts().getId().equals(accountsId)) {
            throw ErrorResponse.of(ErrorCode.IS_ACCOUNTS_DELIVERY);
        }

        delivery.updateAddress(request.getAddress());
        return delivery.toDeliveryDTO();
    }
}
