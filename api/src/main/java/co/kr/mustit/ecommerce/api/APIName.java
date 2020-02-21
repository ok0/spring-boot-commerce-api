package co.kr.mustit.ecommerce.api;

public class APIName {

    // version
    public static final String VERSION = "api/v1";

    // header - content-type, charset
    public static final String CHARSET = "application/json;charset=utf-8";

    // user
    public static final String USER_PREFIX = VERSION + "/user";
    public static final String LOGIN = USER_PREFIX + "/login";
    public static final String LOGOUT = USER_PREFIX + "/logout";

    // oauth
    public static final String OAUTH_PREFIX = VERSION + "/oauth";
    public static final String OAUTH_IMPLICIT_LOGIN = OAUTH_PREFIX + "/implicit/login";
    public static final String CHECK_API_KEY = VERSION + "oauth";

    // api - user
    public static final String USERS = VERSION + "/users";
    public static final String USER_REGISTER = "/register";
    public static final String USER_LIST = "/list";
    public static final String USERS_LOGIN = "/login";
    public static final String USERS_LOGOUT = "/logout";
    public static final String USER_DETAILS = "/detail/{userId}";
}
