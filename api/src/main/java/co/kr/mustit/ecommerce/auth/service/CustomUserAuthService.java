package co.kr.mustit.ecommerce.auth.service;

import co.kr.mustit.ecommerce.auth.AuthUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 *
 */
public interface CustomUserAuthService extends UserDetailsService{
    AuthUser loadUserByAccessToken(String token);
}
