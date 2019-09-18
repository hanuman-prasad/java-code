package com.conf;

import com.resources.SimpleGet2;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/simpleapi2")
public class SimpleApp2 extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(SimpleGet2.class);
        return resources;
    }
}
