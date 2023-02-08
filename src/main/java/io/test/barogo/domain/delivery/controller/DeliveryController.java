package io.test.barogo.domain.delivery.controller;

import io.swagger.annotations.*;
import io.test.barogo.domain.delivery.controller.request.DeliveryCreateRequest;
import io.test.barogo.domain.delivery.entity.dto.DeliveryWithAccountsDTO;
import io.test.barogo.domain.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "배달 컨트롤러")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @ApiOperation(value = "배달 생성"
            , response = DeliveryWithAccountsDTO.class
            , notes = "배달 생성 API"
    )
    @ApiResponses({
            @ApiResponse(code = 401, message = "파라미터 부족"),
            @ApiResponse(code = 403, message = "계정이 존재합니다.")
    })
    @PostMapping
    public ResponseEntity create(
            @ApiParam(value = "배달 생성 Request") @Valid @RequestBody DeliveryCreateRequest request
    ) {
        return ResponseEntity.ok(deliveryService.create(request));
    }




}
