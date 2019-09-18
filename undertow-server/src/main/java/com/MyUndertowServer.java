package com;

import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainer;
import io.undertow.servlet.api.ServletInfo;
import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.util.PortProvider;

import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.List;

import static io.undertow.servlet.Servlets.servlet;

class MyUndertowServer {
    private final PathHandler rootPathHandler = new PathHandler();
    private final ServletContainer container = ServletContainer.Factory.newInstance();
    private Undertow server;

    MyUndertowServer start() {
        server = Undertow.builder()
                .addHttpListener(PortProvider.getPort(), "localhost")
                .setHandler(rootPathHandler)
                .build();

        server.start();
        return this;
    }


    public MyUndertowServer deploy(Application application) {
        return deploy(application, null);
    }

    public MyUndertowServer deploy(List<Application> applications, String basePath) {
        for (Application app : applications) {
            deploy(applications, basePath);
        }

        return this;
    }

    public MyUndertowServer deploy(Application application, String bashPath) {
        DeploymentInfo di = buildResteasyDeploymentInfo(application);
        di.setClassLoader(application.getClass().getClassLoader());
        di.setContextPath(bashPath);
        di.setDeploymentName("Resteasy" + bashPath);
        return deploy(di);
    }

    public void stop() {
        server.stop();
    }

    public MyUndertowServer deploy(DeploymentInfo deploymentInfo) {
        DeploymentManager manager = container.addDeployment(deploymentInfo);
        manager.deploy();

        try {
            rootPathHandler.addPrefixPath(deploymentInfo.getContextPath(), manager.start());
        } catch (ServletException ex) {
            System.out.println(ex.getMessage());
        }

        return this;
    }

    public DeploymentInfo buildResteasyDeploymentInfo(Application application) {

        String mapping = getContextPath(application);

        String prefix = mapping.equals("/*") ? null : mapping.substring(0, mapping.length() - 2);

        ResteasyDeployment resteasyDeployment = new ResteasyDeployment();
        resteasyDeployment.setApplication(application);

        ServletInfo resteasyServlet = servlet("ResteasyServlet", HttpServlet30Dispatcher.class)
                .setAsyncSupported(true)
                .setLoadOnStartup(1)
                .addMapping(mapping);
        if (prefix != null) resteasyServlet.addInitParam("resteasy.servlet.mapping.prefix", prefix);

        return new DeploymentInfo()
                .addServletContextAttribute(ResteasyDeployment.class.getName(), resteasyDeployment)
                .addServlet(resteasyServlet);
    }

    private String getContextPath(Application application) {
        ApplicationPath appContextPath = application.getClass().getAnnotation(ApplicationPath.class);
        String mapping = appContextPath != null ? appContextPath.value() : "/";

        if (mapping == null) mapping = "/";
        if (!mapping.startsWith("/")) mapping = "/" + mapping;
        if (!mapping.endsWith("/")) mapping += "/";
        mapping = mapping + "*";

        return mapping;
    }

}
