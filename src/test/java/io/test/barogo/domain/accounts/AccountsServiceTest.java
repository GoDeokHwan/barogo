package io.test.barogo.domain.accounts;

import io.test.barogo.domain.accounts.controller.request.JoinRequest;
import io.test.barogo.domain.accounts.entity.dto.AccountsDTO;
import io.test.barogo.domain.accounts.service.AccountsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@Slf4j
public class AccountsServiceTest {
    @Autowired
    private AccountsService accountsService;

    @Test
    @Description("회원가입 로그인 중복")
    void 회원가입_로그인_중복() {
        JoinRequest request = JoinRequest.of("system", "홍길동", "1234Qqasdfsd2342342");
        assertThrows(RuntimeException.class, () -> {
            accountsService.create(request);
        });
    }

    @Test
    @Description("회원가입 성공 케이스")
    void 회원가입() {
        JoinRequest request = JoinRequest.of("system1", "홍길동", "1234");
        AccountsDTO accounts = accountsService.create(request);
        assertEquals(accounts.getLoginId(), request.getLoginId());
        assertEquals(accounts.getName(), request.getName());
    }
}
