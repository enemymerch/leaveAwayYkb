package com.mcan.ykb.unitcase.interceptor;

import com.mcan.ykb.unitcase.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("LoginInterceptor preHandle");

        String authorizationHeader = request.getHeader(Constants.HTTP_HEADER.AUTHORIZATION);
        logger.debug("Authorization Header:  " + authorizationHeader);
        boolean isAuthorized = authorizationHeader.equals(Constants.Authorization.basicAuthorizationToken);
        logger.debug("is authorized ? : " + isAuthorized);
        return isAuthorized;
    }
}
