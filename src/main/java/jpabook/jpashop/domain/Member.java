package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Adress adress;

    @OneToMany(mappedBy = "member") //order table에 있는 member에서 매핑이 됨 
    private List<Order> orders = new ArrayList<Order>();
}
