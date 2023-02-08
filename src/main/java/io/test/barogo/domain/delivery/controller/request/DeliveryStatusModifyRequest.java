package io.test.barogo.domain.delivery.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.test.barogo.domain.delivery.entity.enumerate.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@ApiModel(value = "배달 주소 변경 Request", description = "배달 주소 변경 Request")
public class DeliveryStatusModifyRequest {

    @NotNull(message = "변경할 상태는 필수 값입니다.")
    @ApiModelProperty(value = "상태", example = "IN_DELIVERY", required = true)
    DeliveryStatus deliveryStatus;
}
