package io.test.barogo.domain.accounts.controller;

import io.swagger.annotations.*;
import io.test.barogo.domain.accounts.controller.request.JoinRequest;
import io.test.barogo.domain.accounts.entity.dto.AccountsDTO;
import io.test.barogo.domain.accounts.service.AccountsService;
import io.test.barogo.domain.accounts.controller.request.DeliveryAddressModifyRequest;
import io.test.barogo.domain.delivery.entity.dto.DeliveryDTO;
import io.test.barogo.domain.delivery.service.DeliveryService;
import io.test.barogo.support.ErrorCode;
import io.test.barogo.support.ErrorResponse;
import io.test.barogo.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Api(tags = "계정 컨트롤러")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountsController {

    private final AccountsService accountsService;
    private final DeliveryService deliveryService;

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

    @ApiOperation(value = "계정에 속한 배달현황 조회"
            , response = DeliveryDTO.class
            , notes = "계정에 속한 배달현황 조회 API"
    )
    @ApiResponses({
            @ApiResponse(code = 401, message = "파라미터 부족"),
            @ApiResponse(code = 404, message = "계정을 찾을 수 없습니다."),
            @ApiResponse(code = 400, message = "파라미터가 이상합니다.")
    })
    @GetMapping("/{id}/delivery")
    public ResponseEntity getWithDelivery(
            @ApiParam(value = "계정ID", required = true, example = "1") @PathVariable Long id,
            @ApiParam(value = "배달시작일", required = true, example = "2022-01-02") @RequestParam @DateTimeFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT) LocalDate startDate,
            @ApiParam(value = "배달종료일", required = true, example = "2022-01-02") @RequestParam @DateTimeFormat(pattern = DateUtils.DEFAULT_DATE_FORMAT) LocalDate endDate
    ) {
        validDeliveryBetween(startDate, endDate);
        return ResponseEntity.ok(deliveryService.search(id, startDate, endDate));
    }

    private static void validDeliveryBetween(LocalDate starDate, LocalDate endDate) {
        if (endDate.compareTo(starDate) > 3) {
            throw ErrorResponse.of(ErrorCode.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "배달 주소 수정"
            , response = DeliveryDTO.class
            , notes = "배달 주소 수정 API"
    )
    @ApiResponses({
            @ApiResponse(code = 401, message = "파라미터 부족"),
            @ApiResponse(code = 403, message = "배달을 찾을 수 없습니다."),
            @ApiResponse(code = 403, message = "자신의 주문한 배달이 아닙니다.")
    })
    @PatchMapping("/{accountsId}/delivery/{deliveryId}/address")
    public ResponseEntity modifyAddress(
            @ApiParam(value = "계정ID", required = true, example = "1") @PathVariable Long accountsId,
            @ApiParam(value = "배달ID", required = true, example = "1") @PathVariable Long deliveryId,
            @ApiParam(value = "배달 주소 변경 Request") @Valid @RequestBody DeliveryAddressModifyRequest request
    ) {
        return ResponseEntity.ok(deliveryService.modifyAddress(accountsId, deliveryId, request));
    }

}
