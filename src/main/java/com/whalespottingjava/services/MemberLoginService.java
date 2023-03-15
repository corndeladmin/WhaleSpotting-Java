package com.whalespottingjava.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginService {
    public void loginMember(
            HttpServletRequest servletRequest,
            String username,
            String decodedPassword
    ) throws ServletException {
        servletRequest.login(username, decodedPassword);
    }
}
