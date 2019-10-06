package io.devfactory.license.utils;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static UserContext getContext() {

        UserContext context = userContext.get();

        if (ObjectUtils.isEmpty(context)) {
            context = createEmptyContext();
            userContext.set(context);
        }

        return userContext.get();
    }

    public static void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }

    public static UserContext createEmptyContext() {
        return new UserContext();
    }

}
