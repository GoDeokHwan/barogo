package io.test.barogo.domain.accounts.controller;

import io.swagger.annotations.*;
import io.test.barogo.domain.accounts.controller.request.JoinRequest;
import io.test.barogo.domain.accounts.entity.dto.AccountsDTO;
import io.test.barogo.domain.accounts.service.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "계정 컨트롤러")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountsController {

    private final AccountsService accountsService;

    @ApiOperation(value = "회원가입"
            , response = AccountsDTO.class
            , notes = "회원 가입 API"
    )
    @ApiResponses({
            @ApiResponse(code = 401, message = "파라미터 부족"),
            @ApiResponse(code = 403, message = "계정이 존재합니다.")
    })
    @PostMapping("/join")
    public ResponseEntity join(
            @ApiParam(value = "회원가입정보") @Valid @RequestBody JoinRequest request) {
        return ResponseEntity.ok(accountsService.create(request));
    }
}
