package com.ssafy.farmcu.api.service.member;

import com.ssafy.farmcu.api.dto.member.MemberImageDto;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.member.MemberImage;
import com.ssafy.farmcu.api.repository.MemberImageRepository;
import com.ssafy.farmcu.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberImageServiceImpl implements MemberImageService {
    private final MemberRepository memberRepository;
    private final MemberImageRepository memberImageRepository;

    @Transactional
    @Override
    public boolean saveMemberImage(MemberImageDto memberImageDto) {
        try {
            Member member = memberRepository.findByMemberId(memberImageDto.getMemberId()).orElseThrow(NullPointerException::new);
            MemberImage memberImage = MemberImage.builder()
                    .originalName(memberImageDto.getOriginalName())
                    .savedPath(memberImageDto.getSavedPath())
                    .member(member)
                    .build();

            memberImageRepository.save(memberImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public boolean updateMemberImage(Long id, String savedPath, String originalName) {
        try {
            MemberImage memberImage = MemberImage.builder()
                    .memberImageId(id)
                    .savedPath(savedPath)
                    .originalName(originalName).build();

            memberImageRepository.save(memberImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public boolean deleteMemberImage(Long memberImageId) {
        try {
            memberImageRepository.deleteByMemberImageId(memberImageId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public MemberImageDto findMemberImageByMemberId(Long memberId) {
        MemberImage memberImage = memberImageRepository.findByMemberId(memberId).orElse(null);
        if (memberImage == null)
            return null;
        return new MemberImageDto(memberImage);
    }


}
