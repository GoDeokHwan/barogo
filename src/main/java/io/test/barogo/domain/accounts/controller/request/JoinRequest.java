package io.test.barogo.domain.accounts.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.test.barogo.support.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor(staticName = "of")
@ApiModel(value = "회원가입 Request", description = "회원가입정보 Request")
public class JoinRequest {
    @Size(max = 50, message = "로그인ID는 최대 50자입니다.")
    @NotBlank(message = "로그인 ID는 필수 값입니다.")
    @ApiModelProperty(value = "로그인ID", example = "system")
    protected String loginId;
    @Size(max = 30, message = "이름은 최대 30글자입니다.")
    @NotBlank(message = "이름은 필수 값입니다.")
    @ApiModelProperty(value = "이름", example = "홍길동")
    protected String name;
    @Password
    @Size(min = 12, max = 30, message = "패스워드는 최소 12자리부터 최대 30자리입니다.")
    @ApiModelProperty(value = "패스워드", example = "1341324")
    protected String password;
}
