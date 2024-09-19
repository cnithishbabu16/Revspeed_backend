package com.revspeed.backend_p1.config;

import com.revspeed.backend_p1.model.User;
import com.revspeed.backend_p1.service.UserService;
import com.revspeed.backend_p1.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter implements ApplicationContextAware {

    private JwtUtil jwtUtil;
    private ApplicationContext applicationContext;

    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);
                Long userId= jwtUtil.extractUserId(token);

                UserService userService = applicationContext.getBean(UserService.class);

                User user = userService.findByEmail(email);
                if (user != null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(email, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
