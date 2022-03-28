package lv.vladislavs.archiver.util;

import javax.servlet.http.HttpServletRequest;

public class HttpReqRespUtil {
    private static final String[] IP_HEADER_CANDIDATES = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR"
    };

    /**
     * Copied from https://stackoverflow.com/a/55266907/8740600
     */
    public static String getIpAddress(HttpServletRequest httpServletRequest) {
        for (String header: IP_HEADER_CANDIDATES) {
            String potentialIp = httpServletRequest.getHeader(header);
            if (potentialIp != null
                    && !potentialIp.isEmpty()
                    && !"unknown".equalsIgnoreCase(potentialIp)) {
                return potentialIp.split(",")[0];
            }
        }

        return httpServletRequest.getRemoteAddr();
    }
}