package io.devfactory.organization.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class UserContextFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        UserContextHolder.getContext().setInfo(
                httpServletRequest.getHeader(UserContext.CORRELATION_ID),
                httpServletRequest.getHeader(UserContext.AUTH_TOKEN),
                httpServletRequest.getHeader(UserContext.USER_ID),
                httpServletRequest.getHeader(UserContext.ORG_ID)
        );

        log.debug("UserContextFilter: {}", UserContextHolder.getContext().toString());

        chain.doFilter(request, response);
    }

}
