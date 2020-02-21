package co.kr.mustit.ecommerce.api.controller;

import co.kr.mustit.ecommerce.api.response.util.ResponseUtil;
import co.kr.mustit.ecommerce.auth.AuthUser;
import co.kr.mustit.ecommerce.auth.service.CustomUserAuthService;
import co.kr.mustit.ecommerce.configs.AppConfig;
import co.kr.mustit.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 */
public abstract class AbstractBaseController {
    
    @Autowired
    private CustomUserAuthService userDetailsService;

    @Autowired
    AppConfig appConfig;
    
    @Autowired
    protected ResponseUtil responseUtil;
    
    public AuthUser getAuthUserFromSession(HttpServletRequest request) {
        String authToken = request.getHeader(Constant.HEADER_TOKEN);
        // try to load sessio
        AuthUser user = userDetailsService.loadUserByAccessToken(authToken);
        return user;
    }
}
