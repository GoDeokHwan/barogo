package io.test.barogo.domain.accounts.entity;

import io.test.barogo.domain.accounts.entity.dto.AccountsDTO;
import io.test.barogo.domain.delivery.entity.Delivery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Table(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Comment("계정ID")
    private Long id;

    @Column(length = 50)
    @Comment("로그인ID")
    private String loginId;

    @Column(length = 30)
    @Comment("이름")
    private String name;

    @Column(length = 1000)
    @Comment("패스워드")
    private String password;

    @Column(length = 500)
    @Comment("주소")
    private String address;

    @Comment("배달 현황")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accounts")
    private Set<Delivery> deliveries = new HashSet<>();

    public static Accounts ofCreate(String loginId, String name, String password) {
        Accounts instance = new Accounts();
        instance.loginId = loginId;
        instance.name = name;
        instance.password = password;
        return instance;
    }

    public AccountsDTO toAccountDTO() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(this, AccountsDTO.class);
    }
}
