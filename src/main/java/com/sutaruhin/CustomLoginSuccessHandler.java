package com.sutaruhin;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

		System.out.println("✅ ログイン成功: " + authentication.getName());
	    System.out.println("✅ 権限: " + authentication.getAuthorities());


		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("管理者")) {

                response.sendRedirect("/employee/list");
                return;
            } else if (authority.getAuthority().equals("一般")) {
                response.sendRedirect("/report/list");
                return;
            }
        }
        response.sendRedirect("/");
	}
}
