package io.test.barogo.domain.delivery.entity.enumerate;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    WAITING("대기중"),
    IN_DELIVERY("배달중"),
    COMPLETED("완료")
    ;
    private String label;

    DeliveryStatus(String label) {
        this.label = label;
    }

    public boolean isWaiting() {
        return this.equals(WAITING);
    }


    public boolean isInDelivery() {
        return this.equals(IN_DELIVERY);
    }


    public boolean isCompleted() {
        return this.equals(COMPLETED);
    }
}
