package co.kr.mustit.ecommerce.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import co.kr.mustit.ecommerce.api.response.util.APIStatus;
import co.kr.mustit.ecommerce.api.response.model.StatusResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
@Component
public class AuthEntryPointException implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new StatusResponse(APIStatus.ERR_UNAUTHORIZED)));
    }
}
