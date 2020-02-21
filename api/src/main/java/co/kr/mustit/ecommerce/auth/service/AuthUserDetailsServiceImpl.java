package co.kr.mustit.ecommerce.auth.service;

import com.google.gson.Gson;
import co.kr.mustit.ecommerce.api.response.util.APIStatus;
import co.kr.mustit.ecommerce.auth.AuthUser;
import co.kr.mustit.ecommerce.database.model.UserToken;
import co.kr.mustit.ecommerce.exception.ApplicationException;
import co.kr.mustit.ecommerce.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 
 *
 */
@Service
public class AuthUserDetailsServiceImpl implements CustomUserAuthService {
    
    Gson gson = new Gson();
    
    @Autowired
    private UserTokenRepository userTokenRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userId) {
        // Not implement
        return null;
    }
    
    
    @Override
    public AuthUser loadUserByAccessToken(String token) {
        UserToken session = userTokenRepository.findById(token).orElseGet(() -> null);
        if (session != null){
            if (session.getSessionData() != null && !"".equals(session.getSessionData())){
                AuthUser authUser = gson.fromJson(session.getSessionData(), AuthUser.class);
                return authUser;
            }else{
                throw new ApplicationException(APIStatus.ERR_SESSION_DATA_INVALID);
            }
        }else{
            throw new ApplicationException(APIStatus.ERR_SESSION_NOT_FOUND);
        }
    }
}
