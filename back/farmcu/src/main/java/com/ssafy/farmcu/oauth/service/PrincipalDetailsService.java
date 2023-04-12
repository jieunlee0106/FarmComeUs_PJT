package com.ssafy.farmcu.oauth.service;

import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.oauth.PrincipalDetails;
import com.ssafy.farmcu.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findById(id);

        if (member == null) {
            throw new UsernameNotFoundException("cant find");
        }
        return (UserDetails) new PrincipalDetails(member.get());
    }
}
