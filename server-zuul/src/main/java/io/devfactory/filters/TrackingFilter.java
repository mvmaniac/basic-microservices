package io.devfactory.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.devfactory.config.ServiceConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    private final FilterUtils filterUtils;

    private final ServiceConfig config;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() {

        if (isCorrelationIdPresent()) {
            log.debug("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
            log.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
        }

        String authorizationToken = filterUtils.getAuthToken();

        if (null == authorizationToken) {
            authorizationToken = filterUtils.getAuthorizationToken();
            filterUtils.setAuthToken(authorizationToken);
        }

        log.debug("The organization id from the token is: {}.", getOrganizationId(authorizationToken));

        RequestContext ctx = RequestContext.getCurrentContext();
        log.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());

        return null;
    }

    private String getOrganizationId(String authorizationToken) {

        String result = "";
        String authToken = authorizationToken.replace("Bearer ", "");

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(config.getSigningKey().getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(authToken).getBody();

            result = (String) claims.get("organizationId");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private boolean isCorrelationIdPresent() {
        return filterUtils.getCorrelationId() != null;
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

}
