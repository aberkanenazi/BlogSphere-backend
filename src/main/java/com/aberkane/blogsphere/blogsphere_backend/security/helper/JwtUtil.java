package com.aberkane.blogsphere.blogsphere_backend.security.helper;

import com.aberkane.blogsphere.blogsphere_backend.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import com.aberkane.blogsphere.blogsphere_backend.dto.LoginResponseDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.RoleDto;
import com.aberkane.blogsphere.blogsphere_backend.model.User;
import com.aberkane.blogsphere.blogsphere_backend.repository.UserRepository;
import com.aberkane.blogsphere.blogsphere_backend.security.context.RequestContext;
import com.aberkane.blogsphere.blogsphere_backend.security.contstant.SecurityConstants;
import com.aberkane.blogsphere.blogsphere_backend.services.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {

    @Value("${document.builder.secret}")
    private String jwtSecret;

    @Value("${document.builder.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RequestContext requestContext;

    public LoginResponseDto generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> user = userRepository.findByEmail(userPrincipal.getUsername());
        return LoginResponseDto.builder().token(Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact()).role(modelMapper.map(user.orElse(null).getRole(), RoleDto.class)).build();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;

        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        } catch (Exception exception) {
            exception.getStackTrace();
        }
        return false;
    }

    public void setRequestContextDetails(UserDetails userDetails, HttpServletRequest request) {
        requestContext.setJwtHeader(request.getHeader(SecurityConstants.JWT_HEADER));
        requestContext.setRoles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        requestContext.setIpAddress(request.getLocalAddr());
        requestContext.setPreferredUserName(userDetails.getUsername());
    }
}
