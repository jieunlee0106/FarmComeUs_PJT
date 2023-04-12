package com.ssafy.farmcu.api.service.member;

import com.ssafy.farmcu.api.dto.member.*;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.exception.NotFoundUserException;
import com.ssafy.farmcu.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder pwEncoder;

    @Transactional
    @Override
    public boolean createMember(MemberJoinReq memberJoinInfo) {

        log.debug("memberJoinInfo DTO : {}", memberJoinInfo);
        if(memberRepository.findById(memberJoinInfo.getId()).isPresent()){
            return false;
        }
        String pw = pwEncoder.encode(memberJoinInfo.getPassword().toString());

        memberJoinInfo.updatePW(pw);
        Member newMember = memberRepository.save(memberJoinInfo.ToEntity());
        System.out.println(newMember.getCreatedAt());
        return true;
    }

    @Transactional(readOnly = true)
    public MemberDto getUserInfo(Long id){
        return memberRepository.findByMemberId(id).map(MemberDto::of).orElseThrow(() -> new NotFoundUserException("아이디를 가진 사람이 없습니다."));
    }


    @Transactional(readOnly = true)
    public Member findUser(String id){
        Member member =memberRepository.findById(id).orElse(null);
        return member;
    }

    @Transactional
    @Override
    public boolean deleteMember(MemberLoginReq memberLoginInfo) {
        Optional<Member> member = memberRepository.findById(memberLoginInfo.getId());
        if(pwEncoder.matches(memberLoginInfo.getPassword(), member.get().getPassword())){
            memberRepository.delete(member.get());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateMember(MemberUpdateReq memberUpdateReq, String id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundUserException("아이디를 가진 사람이 없습니다."));
//        if(!pwEncoder.matches(member.getPassword(), member.getPassword())){ // 비밀번호 검증
//            return false;
//        }

        member.updateInfo(memberUpdateReq);
        memberRepository.save(member);
        return true;
    }

    @Transactional
    public boolean updateMember(MemberAdditionalReq memberAdditionalReq, String id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundUserException("아이디를 가진 사람이 없습니다."));
//        if(!pwEncoder.matches(member.getPassword(), member.getPassword())){ // 비밀번호 검증
//            return false;
//        }

        member.updateAddInfo(memberAdditionalReq);
        memberRepository.save(member);
        return true;
    }
}

