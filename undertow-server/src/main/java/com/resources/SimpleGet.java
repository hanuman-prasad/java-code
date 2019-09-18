package com.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class SimpleGet {

    @GET
    @Produces("text/html")
    public String getSampleMessage(){
        return "<h1>Testing\n rest easy api ..</h1>..";
    }
}
