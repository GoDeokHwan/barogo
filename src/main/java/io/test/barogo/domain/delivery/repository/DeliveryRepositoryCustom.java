package io.test.barogo.domain.delivery.repository;

import io.test.barogo.domain.delivery.entity.Delivery;
import io.test.barogo.support.validation.Password;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DeliveryRepositoryCustom {

    List<Delivery> findByAccountsIdAndRequestTimeBetween(Long accountsId, LocalDateTime startDate, LocalDateTime endDate);

    Optional<Delivery> findByIdWithAccounts(Long id);
}
