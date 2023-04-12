package com.ssafy.farmcu.api.entity.live;

import com.ssafy.farmcu.api.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "live_like")
public class LiveLike {

    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long liveLikeId;

    // 연결
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Live.class)
    @JoinColumn(name = "live_id", updatable = false)
    private Live live;

}

