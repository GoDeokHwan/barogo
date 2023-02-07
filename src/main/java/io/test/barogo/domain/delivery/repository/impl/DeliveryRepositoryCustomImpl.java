package io.test.barogo.domain.delivery.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.test.barogo.domain.delivery.entity.Delivery;
import io.test.barogo.domain.delivery.repository.DeliveryRepositoryCustom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static io.test.barogo.domain.delivery.entity.QDelivery.delivery;
import static io.test.barogo.domain.accounts.entity.QAccounts.accounts;


public class DeliveryRepositoryCustomImpl implements DeliveryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public DeliveryRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Delivery> findByAccountsIdAndRequestTimeBetween(Long accountsId, LocalDateTime startDate, LocalDateTime endDate) {
        return (List<Delivery>) jpaQueryFactory
                .from(delivery)
                .join(delivery.accounts, accounts)
                .where(accounts.id.eq(accountsId)
                        .and(delivery.requestTime.between(startDate, endDate))
                )
                .fetch();
    }
}
