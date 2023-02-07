package io.test.barogo.domain.delivery.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@ApiModel(value = "배달 주소 변경 Request", description = "배달 주소 변경 Request")
public class DeliveryAddressModifyRequest {

    @NotBlank(message = "주소는 필수 값입니다.")
    @ApiModelProperty(value = "주소", example = "서울시 강남구", required = true)
    private String address;
}
