package com.localfusion.server;

import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * entry point of the server
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.localfusion.server")
public class BootApplication {

    /**
     * http port constant
     */
    @Value("${http.port}")
    private int httpPort;

    /**
     * https port constant
     */
    @Value("${server.port}")
    private int httpsPort;

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    /**
     * forward http requests to https
     */
    @Bean
    ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(httpPort);
        connector.setSecure(false);
        connector.setRedirectPort(httpsPort);
        serverFactory.addAdditionalTomcatConnectors(connector);
        return serverFactory;
    }

}
