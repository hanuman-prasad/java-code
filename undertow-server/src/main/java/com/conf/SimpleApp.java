package com.conf;

import com.resources.SimpleGet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/simpleapi")
public class SimpleApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(SimpleGet.class);
        return resources;
    }
}
