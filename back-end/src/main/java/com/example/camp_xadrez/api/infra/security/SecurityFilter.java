package com.example.camp_xadrez.api.infra.security;

import com.example.camp_xadrez.api.domain.professor.ProfessorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProfessorRepository professorRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = professorRepository.findByEmail(subject);

            professorRepository.findByEmail(subject).ifPresent(professor -> {
                var authentication =
                        new UsernamePasswordAuthenticationToken(professor, null, professor.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
            System.out.println("LOGADO NA REQUISICAO");
        }

        filterChain.doFilter(request, response);
    }
    private String recuperarToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if(authorizationHeader != null){
        return authorizationHeader.replace("Bearer", "").trim();
    }
        return null;
    }

}
