package co.kr.mustit.ecommerce.service.auth;

import co.kr.mustit.ecommerce.database.model.User;
import co.kr.mustit.ecommerce.database.model.UserToken;

/**
 *
 *
 */
public interface AuthService {
    
    public User getUserByEmailAndCompanyIdAndStatus(String email, Long companyId, int status);

    public UserToken createUserToken(User adminUser, boolean keepMeLogin);

    public User getUserByUserIdAndCompanyIdAndStatus(String userId, Long companyId, int status);

    public UserToken getUserTokenById(String id);

    public void deleteUserToken(UserToken userToken);
}
