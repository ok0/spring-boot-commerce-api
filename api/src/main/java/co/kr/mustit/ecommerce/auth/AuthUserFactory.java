package co.kr.mustit.ecommerce.auth;

import co.kr.mustit.ecommerce.database.model.User;

/**
 *
 *
 */
public interface AuthUserFactory {
    
    AuthUser createAuthUser(User user);
}
