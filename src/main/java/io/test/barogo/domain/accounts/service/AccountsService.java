package io.test.barogo.domain.accounts.service;

import io.test.barogo.domain.accounts.controller.request.JoinRequest;
import io.test.barogo.domain.accounts.entity.dto.AccountsDTO;

public interface AccountsService {
    AccountsDTO create(JoinRequest request);
}
