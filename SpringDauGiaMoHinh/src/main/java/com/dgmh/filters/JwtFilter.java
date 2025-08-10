package com.dgmh.filters;

import com.dgmh.utils.JwtUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 0) BỎ QUA preflight CORS
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String contextPath = httpRequest.getContextPath();   // ví dụ: /SpringDauGiaMoHinh
        String uri = httpRequest.getRequestURI();            // ví dụ: /SpringDauGiaMoHinh/api/phiendaugianguoidung/lich-su/5

        // 1) CHO PHÉP PUBLIC: /api/phiendaugianguoidung/lich-su/**
        if (uri.startsWith(contextPath + "/api/phiendaugianguoidung/lich-su/")) {
            chain.doFilter(request, response);
            return;
        }

        // 2) Chỉ kiểm tra token với các nhóm path cần bảo vệ
        if (uri.contains("/api/secure")
                || uri.contains("/api/sanpham")
                || uri.contains("/api/phiendaugianguoidung")
                || uri.contains("/api/theodoisanpham")) {

            String header = httpRequest.getHeader("Authorization");
            System.out.println("URI: " + uri + " | HEADER nhận từ FE: " + header);

            if (header == null || !header.startsWith("Bearer ")) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.");
                return;
            }

            String token = header.substring(7);
            try {
                String username = JwtUtils.validateTokenAndGetUsername(token);
                if (username != null) {
                    System.out.println("Token hợp lệ, user: " + username);
                    httpRequest.setAttribute("username", username);

                    // QUAN TRỌNG: không để authorities = null
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    chain.doFilter(request, response);
                    return;
                } else {
                    System.out.println("Token hết hạn hoặc không hợp lệ");
                }
            } catch (Exception e) {
                System.out.println("Lỗi JWT: " + e.getMessage());
                e.printStackTrace();
            }

            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token không hợp lệ hoặc hết hạn");
            return;
        }

        // 3) Các request còn lại bỏ qua filter
        chain.doFilter(request, response);
    }
}
