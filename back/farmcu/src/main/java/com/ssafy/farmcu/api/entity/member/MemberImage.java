package com.ssafy.farmcu.api.entity.member;

import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.entity.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member_image")
public class MemberImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long memberImageId;

    @Column
    private String originalName;

    @Column
    private String savedPath;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @Builder
    public MemberImage(Long memberImageId, String originalName, String savedPath, Member member) {
        this.memberImageId = memberImageId;
        this.originalName = originalName;
        this.savedPath = savedPath;
        this.member = member;
    }

}
