package co.kr.mustit.ecommerce.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import co.kr.mustit.ecommerce.api.response.model.StatusResponse;
import co.kr.mustit.ecommerce.auth.AuthUser;
import co.kr.mustit.ecommerce.auth.service.CustomUserAuthService;
import co.kr.mustit.ecommerce.configs.AppConfig;
import co.kr.mustit.ecommerce.exception.ApplicationException;
import co.kr.mustit.ecommerce.tracelogged.EventLogManager;
import co.kr.mustit.util.Constant;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *
 */
public abstract class AbstractAPI {

    @Autowired
    private CustomUserAuthService userDetailsService;

    //
    // Build setting for Gson class accept NULL value
    //
    Gson gson = new GsonBuilder()
            .serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(Constant.API_FORMAT_DATE)
            .create();

    // Mapper object is used to convert object and etc...
    public final static ObjectMapper mapper = new ObjectMapper();



    static {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
                .setSerializationInclusion(JsonInclude.Include.ALWAYS)
                .setDateFormat(new SimpleDateFormat(Constant.API_FORMAT_DATE));
    }

    @Autowired
    AppConfig appConfig;

    //
    // Create logger
    //
    public final static EventLogManager logger = EventLogManager.getInstance();

    //
    // Write object as string using mapper
    //
    protected String writeObjectToJson(Object obj) {
        try {

            return mapper.writeValueAsString(obj);

        } catch (JsonProcessingException ex) {
            // Throw our exception
            throw new ApplicationException(ex.getCause());
        }
    }

    protected String writeObjectToJsonRemoveNullProperty(Object obj) throws ApplicationException
    {
        try {
            // Set setting remove NULL property
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // map json
            String result=mapper.writeValueAsString(obj);
            // Reset default setting
            mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            return  result;

        } catch (JsonProcessingException ex) {
            // Throw our exception
            throw new ApplicationException(ex.getCause());
        }
    }

    //
    // Reponse status
    //
    public StatusResponse statusResponse = null;

    public AuthUser getAuthUserFromSession(HttpServletRequest request) {
        String authToken = request.getHeader(Constant.HEADER_TOKEN);
        // try to load session
        AuthUser user = userDetailsService.loadUserByAccessToken(authToken);
        return user;
    }
}
