package com.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test2")
public class SimpleGet2 {

    @GET
    @Produces("text/html")
    public String getSampleMessage(){
        return "<h1>Testing simple 2 rest easy api ..</h1>..";
    }
}
