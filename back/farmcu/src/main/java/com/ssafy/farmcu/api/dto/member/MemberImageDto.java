package com.ssafy.farmcu.api.dto.member;

import com.ssafy.farmcu.api.entity.member.MemberImage;
import com.ssafy.farmcu.api.entity.store.StoreImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberImageDto {

    private Long memberImageId;
    private String originalName;
    private String savedPath;
    private Long memberId;

    public MemberImageDto(MemberImage memberImage) {
        this.memberImageId = memberImage.getMemberImageId();
        this.originalName = memberImage.getOriginalName();
        this.savedPath = memberImage.getSavedPath();
        this.memberId = memberImage.getMember().getMemberId();
    }

}
