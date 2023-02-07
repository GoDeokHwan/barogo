package io.test.barogo.domain.delivery.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.test.barogo.domain.accounts.entity.dto.AccountsDTO;
import lombok.*;

@Getter
@Setter
@ApiModel(value = "배달, 계정 DTO", description = "배달, 계정 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeliveryWithAccountsDTO extends DeliveryDTO {
    @ApiModelProperty(value = "계정", example = "{" +
            "\"id\":1" +
            ", \"loginId\": \"system\"" +
            ", \"name\":\"홍길동\"" +
            "}")
    protected AccountsDTO accounts;
}
