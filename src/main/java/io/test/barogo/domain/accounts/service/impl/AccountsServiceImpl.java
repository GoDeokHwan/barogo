package io.test.barogo.domain.accounts.service.impl;

import io.test.barogo.domain.accounts.repository.AccountsRepository;
import io.test.barogo.domain.accounts.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;
}
