package com.whalespottingjava.services;

import com.whalespottingjava.models.database.Member;
import com.whalespottingjava.models.requests.MemberRegistrationRequest;
import com.whalespottingjava.repositories.MemberRepository;
import com.whalespottingjava.util.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegistrationService {
    private final MemberRepository memberRepository;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public MemberRegistrationService(
            MemberRepository memberRepository,
            AuthenticationHelper authenticationHelper
    ) {
        this.memberRepository = memberRepository;
        this.authenticationHelper = authenticationHelper;
    }

    public Member registerMember(MemberRegistrationRequest request) {
        String encodedPassword = authenticationHelper.passwordEncoder().encode(request.getPassword());
        return memberRepository.save(new Member(request.getUsername(), encodedPassword, request.getEmail(), request.getFirstName(), request.getSurname()));
    }
}
