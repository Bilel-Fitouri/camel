package com.myexamples.route;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by bfitouri on 12/02/16.
 */
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(Application.class, args);

        CamelSpringBootApplicationController configurableApplicationContextBean =
                configurableApplicationContext.getBean(CamelSpringBootApplicationController.class);

        configurableApplicationContextBean.blockMainThread();
    }

    @Bean
    public MyBean myBean(){
        return new MyBean();
    }
}
