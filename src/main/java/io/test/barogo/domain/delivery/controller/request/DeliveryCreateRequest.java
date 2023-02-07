package io.test.barogo.domain.delivery.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor(staticName = "of")
@ApiModel(value = "배달 생성 Request", description = "배달 생성 Request")
public class DeliveryCreateRequest {

    @NotNull(message = "계정 ID는 필수 값입니다.")
    @ApiModelProperty(value = "계정 ID", example = "1")
    private Long accountsId;

    @NotBlank(message = "주소는 필수 값입니다.")
    @ApiModelProperty(value = "주소", example = "서울시 강남구")
    private String address;
}
