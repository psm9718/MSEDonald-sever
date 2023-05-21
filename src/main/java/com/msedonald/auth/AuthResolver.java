package com.msedonald.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class AuthResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserAuth = parameter.getParameterType().equals(UserAuth.class);

        return isAnnotation && isUserAuth;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorization = webRequest.getHeader("Authorization");
        log.info("auth: {}", authorization);

        if (authorization == null || authorization.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        return UserAuth.builder()
                .id(1L)
                .username(authorization)
                .build();
    }
}
