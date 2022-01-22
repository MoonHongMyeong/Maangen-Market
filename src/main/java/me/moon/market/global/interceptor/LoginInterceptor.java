package me.moon.market.global.interceptor;

import me.moon.market.global.annotation.LoginRequired;
import me.moon.market.global.dto.SessionUser;
import me.moon.market.global.error.exception.ErrorCode;
import me.moon.market.global.error.exception.UnAuthorizedAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String USER = "USER";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        SessionUser user = (SessionUser) request.getSession().getAttribute(USER);

        if(handlerMethod.hasMethodAnnotation(LoginRequired.class) && user == null){
            throw new UnAuthorizedAccessException("UnAuthorized Access", ErrorCode.UNAUTHORIZED_ACCESS);
        }
        return true;
    }
}
