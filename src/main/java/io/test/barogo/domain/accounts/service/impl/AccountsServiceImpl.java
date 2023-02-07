package io.test.barogo.domain.accounts.service.impl;

import io.test.barogo.domain.accounts.controller.request.JoinRequest;
import io.test.barogo.domain.accounts.entity.Accounts;
import io.test.barogo.domain.accounts.entity.dto.AccountsDTO;
import io.test.barogo.domain.accounts.repository.AccountsRepository;
import io.test.barogo.domain.accounts.service.AccountsService;
import io.test.barogo.support.ErrorCode;
import io.test.barogo.support.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;

    @Override
    public AccountsDTO create(JoinRequest request) {
        if (accountsRepository.findByLoginId(request.getLoginId()).isPresent()) {
            throw ErrorResponse.of(ErrorCode.IS_FORBIDDEN_ACCOUTS);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Accounts accounts = accountsRepository.save(
                Accounts.ofCreate(request.getLoginId(), request.getName(), passwordEncoder.encode(request.getPassword()))
        );
        return accounts.toAccountDTO();
    }
}
