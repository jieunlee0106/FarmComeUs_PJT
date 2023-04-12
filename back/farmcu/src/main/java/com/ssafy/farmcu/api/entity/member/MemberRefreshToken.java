package com.ssafy.farmcu.api.entity.member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_REFRESH_TOKEN")
public class MemberRefreshToken {

    @JsonIgnore
    @Id
    @Column(name = "REFRESH_TOKEN_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenSeq;

    @Column(name = "ID", length = 512, unique = true)
    @NotNull
    private String id;

    @Column(name = "REFRESH_TOKEN", length = 256)
    @NotNull
    private String refreshToken;

    @Builder
    public MemberRefreshToken( String id,  String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }

}
