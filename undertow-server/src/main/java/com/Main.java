package com;

import com.conf.SimpleApp;
import com.conf.SimpleApp2;
import org.jboss.resteasy.test.TestPortProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import static com.HitUrl.slp;

public class Main {
    public static void main(String[] args) {
        MyUndertowServer server = new MyUndertowServer().start();
        new Thread(new HitUrl()).start();
        server.deploy(new SimpleApp2(),"/aaa");
        server.deploy(new SimpleApp(),"/aaa");
        slp(60);
        server.stop();
    }
}

class HitUrl implements Runnable{

    @Override
    public void run() {
        slp(10);
        System.out.println("Hitting url...");

        Client client = ClientBuilder.newClient();
        String url =  TestPortProvider.generateURL("/simpleapi/test");
        System.out.println(url);
        String val = client.target(url).request().get(String.class);

        System.out.println(val);
        client.close();
    }

    public static void slp(int i){
        try {
            Thread.sleep(1000 * i);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
