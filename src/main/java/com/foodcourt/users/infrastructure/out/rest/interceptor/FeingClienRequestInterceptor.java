package com.foodcourt.users.infrastructure.out.rest.interceptor;

import com.foodcourt.users.infrastructure.shared.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class FeingClienRequestInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest currentRequest = getCurrentHttpRequest();
        if (currentRequest != null) {
            String authHeader = currentRequest.getHeader(Constants.AUTH_HEADER_NAME);
            if (authHeader != null && authHeader.startsWith(Constants.AUTH_TOKEN_PREFIX)) {
                requestTemplate.header(Constants.AUTH_HEADER_NAME, authHeader);
            }
        }

    }

    private HttpServletRequest getCurrentHttpRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (IllegalStateException | ClassCastException e) {
            return null;
        }
    }
}
