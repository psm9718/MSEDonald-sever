package com.msedonald.auth;

import com.msedonald.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserAuth = parameter.getParameterType().equals(UserAuth.class);

        return isAnnotation && isUserAuth;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String authToken = webRequest.getHeader("Authorization");
        log.info("auth: {}", authToken);

        if (authToken == null || authToken.isEmpty()) {
            throw new UnAuthorizedException();
        }

        if (jwtProvider.isValidateToken(authToken)) {
            return UserAuth.builder()
                    .id(jwtProvider.getUserIdFromJwt(authToken))
                    .username(jwtProvider.getUsernameFromJwt(authToken))
                    .build();
        }

        throw new UnAuthorizedException();
    }
}
