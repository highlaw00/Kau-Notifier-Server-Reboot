package kauproject.kaunotifier.utils;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class HttpLogMessage {
    static final String[] contentKeys = {
            "Request Url", "Client IP", "Http Method", "Query String"
    };

    static final String[] headerTypes = {"X-Forwarded-For", "Proxy-Client-IP",
            "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

    public static String createLog(ServletRequest request) {
        Map<String, String> requestInfoMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String clientIP = null;

        for (String key: contentKeys) {
            requestInfoMap.put(key, null);
        }

        if (request instanceof HttpServletRequest) {
            requestInfoMap.put("Request Url", ((HttpServletRequest) request).getRequestURI());
            requestInfoMap.put("Http Method", ((HttpServletRequest) request).getMethod());
            requestInfoMap.put("Query String", ((HttpServletRequest) request).getQueryString());

            for(String headerType: headerTypes) {
                clientIP = ((HttpServletRequest) request).getHeader(headerType);
                if(clientIP != null) {
                    requestInfoMap.put("Client IP", clientIP);
                    break;
                }
            }

            if (clientIP == null) requestInfoMap.put("Client IP",request.getRemoteAddr());
        }

        for (String key: contentKeys) {
            sb.append("\n");
            sb.append(key);
            sb.append(": ");
            sb.append(requestInfoMap.get(key));
        }

        return sb.toString();
    }
}
