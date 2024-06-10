package kauproject.kaunotifier.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import kauproject.kaunotifier.utils.HttpLogMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class RequestLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
        로깅 요소
        1. Request URI
        2. Client IP
        3. HTTP Method
        4. Request Param
         */
        String logMessage = HttpLogMessage.createLog(request);
        log.info("Request Info {}", logMessage);
        chain.doFilter(request, response);
    }
}
