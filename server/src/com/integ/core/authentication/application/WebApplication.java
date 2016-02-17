package com.integ.core.authentication.application;

import com.integ.core.authentication.handler.AuthenticationHandler;
import com.integ.core.authentication.handler.UserHandler;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Author: Manan
 * Date: 17-12-2015 20:06
 */

@ApplicationPath("auth")
public class WebApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(AuthenticationHandler.class);
        resources.add(UserHandler.class);
    }
}
