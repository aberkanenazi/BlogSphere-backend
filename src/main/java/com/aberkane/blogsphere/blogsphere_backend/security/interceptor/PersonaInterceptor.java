package com.aberkane.blogsphere.blogsphere_backend.security.interceptor;

import com.aberkane.blogsphere.blogsphere_backend.security.context.RequestContext;
import com.aberkane.blogsphere.blogsphere_backend.security.contstant.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@Component
public class PersonaInterceptor extends OncePerRequestFilter {

    @Autowired
    RequestContext requestContext;

    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String personaId= request.getHeader(SecurityConstants.PERSONA_ID);
        String persona = request.getHeader(SecurityConstants.PERSONA);
        if (null != personaId) {
            requestContext.setPersonaId(personaId);

        }
        if(persona !=null){
            requestContext.setPersona(persona);
        }
        filterChain.doFilter(request, response);
    }

}
