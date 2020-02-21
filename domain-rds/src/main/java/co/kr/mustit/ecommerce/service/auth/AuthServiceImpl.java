package co.kr.mustit.ecommerce.service.auth;

import co.kr.mustit.ecommerce.api.response.util.APIStatus;
import co.kr.mustit.ecommerce.auth.AuthUser;
import co.kr.mustit.ecommerce.auth.AuthUserFactory;
import co.kr.mustit.ecommerce.database.model.User;
import co.kr.mustit.ecommerce.database.model.UserToken;
import co.kr.mustit.ecommerce.exception.ApplicationException;
import co.kr.mustit.ecommerce.repository.UserRepository;
import co.kr.mustit.ecommerce.repository.UserTokenRepository;
import co.kr.mustit.ecommerce.service.AbstractBaseService;
import co.kr.mustit.util.Constant;
import co.kr.mustit.util.DateUtil;
import co.kr.mustit.util.UniqueID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 *
 */
@Component
public class AuthServiceImpl extends AbstractBaseService implements AuthService{
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private UserTokenRepository userTokenRepository;
    
    @Autowired
    AuthUserFactory authUserFactory;
    
    @Override
    public UserToken createUserToken (User userLogin, boolean keepMeLogin){
        try {
            UserToken userToken = new UserToken();
            userToken.setToken(UniqueID.getUUID());
            userToken.setCompanyId(userLogin.getCompanyId());
            userToken.setUserId(userLogin.getUserId());
            Date currentDate = new Date();
            userToken.setLoginDate(DateUtil.convertToUTC(currentDate));
            Date expirationDate = keepMeLogin ? new Date(currentDate.getTime() + Constant.DEFAULT_REMEMBER_LOGIN_MILISECONDS) : new Date(currentDate.getTime() + Constant.DEFAULT_SESSION_TIME_OUT);
            userToken.setExpirationDate(DateUtil.convertToUTC(expirationDate));
            AuthUser authUser = authUserFactory.createAuthUser(userLogin);
            // Set authUser to session data...
            userToken.setSessionData(gson.toJson(authUser));
            userTokenRepository.save(userToken);
            return userToken;
        } catch (Exception e) {
            LOGGER.error("Error create User token ", e);
            throw new ApplicationException(APIStatus.SQL_ERROR);
        }
    }

    @Override
    public User getUserByEmailAndCompanyIdAndStatus(String email, Long companyId, int status) {
        return userRepository.findByEmailAndCompanyIdAndStatus(email, companyId, Constant.USER_STATUS.ACTIVE.getStatus());
    }

    @Override
    public User getUserByUserIdAndCompanyIdAndStatus(String userId, Long companyId, int status) {
        return userRepository.findByUserIdAndCompanyIdAndStatus(userId ,companyId, Constant.USER_STATUS.ACTIVE.getStatus());
    }

    @Override
    public UserToken getUserTokenById(String id) {
        return userTokenRepository.findOne(id);
    }

    @Override
    public void deleteUserToken(UserToken userToken) {
        userTokenRepository.delete(userToken);
    }
    
}
