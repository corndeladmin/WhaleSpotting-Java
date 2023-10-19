package com.whalespottingjava.services;

import com.whalespottingjava.models.database.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.whalespottingjava.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberUpdateService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberUpdateService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void updatePassword(Member member){
        memberRepository.saveAndFlush(member);
    }

    public void changePassWordByUsername(String name, String newPassword) {
        Member member = memberRepository.findByUsername(name);

        if (member == null) {
            throw new UsernameNotFoundException("Could not find member with username");
        }

        member.setPassword(newPassword);
    }
}
