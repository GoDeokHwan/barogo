package io.test.barogo.domain.accounts.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ApiModel(value = "계정 Base DTO", description = "계정 Entity에 대한 Base DTO")
public class AccountsDTO {
    @ApiModelProperty(value = "계정ID", example = "1")
    protected Long id;
    @ApiModelProperty(value = "로그인ID", example = "system")
    protected String loginId;
    @ApiModelProperty(value = "이름", example = "홍길동")
    protected String name;
}
