package io.test.barogo.domain.delivery.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.test.barogo.domain.delivery.entity.enumerate.DeliveryStatus;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ApiModel(value = "배달 Entity Base DTO", description = "배달 Entity Base DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeliveryDTO {
    @ApiModelProperty(value = "배달 ID", example = "1")
    protected Long id;
    @ApiModelProperty(value = "배달시작시간", example = "2023-02-06 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime requestTime;
    @ApiModelProperty(value = "배달완료시간", example = "2023-02-06 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime endTime;
    @ApiModelProperty(value = "배달 주소", example = "서울특별시 그짝")
    protected String address;
    @ApiModelProperty(value = "배달상태", example = "COMPLETED")
    protected DeliveryStatus status;
}
