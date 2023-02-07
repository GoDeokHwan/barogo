package io.test.barogo.domain.delivery.entity;

import io.test.barogo.domain.accounts.entity.Accounts;
import io.test.barogo.domain.delivery.entity.dto.DeliveryDTO;
import io.test.barogo.domain.delivery.entity.dto.DeliveryWithAccountsDTO;
import io.test.barogo.domain.delivery.entity.enumerate.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Comment("배달ID")
    private Long id;

    @Column
    @Comment("배달요청시간")
    private LocalDateTime requestTime;

    @Column
    @Comment("배달완료시간")
    private LocalDateTime endTime;

    @Column(length = 500)
    @Comment("주소")
    private String address;

    @Comment("계정")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accounts_id", referencedColumnName = "id")
    private Accounts accounts;

    @Column(length = 10)
    @Comment("배달상태")
    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    public static Delivery ofCreate(Accounts accounts, String address) {
        Delivery instance = new Delivery();
        instance.requestTime = LocalDateTime.now();
        instance.address = address;
        instance.status =DeliveryStatus.WAITING;
        instance.accounts = accounts;
        return instance;
    }

    public DeliveryDTO toDeliveryDTO() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(this, DeliveryDTO.class);
    }

    public DeliveryWithAccountsDTO toDeliveryWidhAccountsDTO() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(this, DeliveryWithAccountsDTO.class);
    }
}
