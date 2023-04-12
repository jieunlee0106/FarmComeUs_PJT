package com.ssafy.farmcu.api.entity.store;

import com.ssafy.farmcu.api.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "store_like")
public class StoreLike {
    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storelike_id")
    private Long id;

    //연결
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Store.class)
    @JoinColumn(name = "store_id", updatable = false)
    private Store store;

    @Builder
    public StoreLike( Long id, Member member, Store store){
        this.id = id;
        this.member = member;
        this.store= store;
    }

}

