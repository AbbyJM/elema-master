package com.abby.elema.model.constants;

/**
 * @author: Abby
 */
public class HttpConstants {

    /**
     * the default login page uri
     */
    public static final String DEFAULT_LOGIN_PAGE="/login";

    /**
     * the default login process uri
     */
    public static final String DEFAULT_LOGIN_PROCESS_URI="/authenticate";


    public static final String UNKNOWN="unknown";

    public static final String X_REAL_IP="X-Real-IP";

    public static final String X_FORWARDED_FOR="X-Forwarded-For";

    public static final String PROXY_CLIENT_IP="Proxy-Client-IP";

    public static final String WL_PROXY_CLIENT_IP="WL-Proxy-Client-IP";

    public static final String HTTP_CLIENT_IP="HTTP_CLIENT_IP";

    public static final String HTTP_X_FORWARDED_FOR="HTTP_X_FORWARDED_FOR";

    public static final String LOCAL_HOST="127.0.0.1";

    public static final String HEADER_AUTHORIZATION="authorization";

    public static final String LOG_URL="http://127.0.0.1:9999/uaac/admin/log/save";

    public static final String ACCESS_TOKEN_URL="http://localhost:9999/uaac/oauth/token";

    public static final String USER_ACTIVE_URL="http://localhost:9999/uaac/api/user/active";
}
