package com.myexamples.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by bfitouri on 12/02/16.
 */
@Component
public class AnimalRoute extends RouteBuilder {

    public static final String CAMEL_FILE_NAME = "CamelFileName";

    @Autowired
    private MyAggregator aggregator;

    @Value("${start.something}")
    private String startEndpoint;

    @Override
    public void configure() throws Exception {
        //from file to file
        from("{{animalSource}}")
                .log("got message")
                .choice()
                .when(p -> p.getIn().getHeader(CAMEL_FILE_NAME).toString().contains("dog"))
                .log("found a dog!")
                .to("{{dogEndpoint}}")
                .when(p -> p.getIn().getHeader(CAMEL_FILE_NAME).toString().contains("cat"))
                .log("looks like a cat!")
                .to("{{catEndpoint}}");

        //from stream:in, to stream:file then spring bean
        from("stream:in?promptMessage=Enter something: ")
                .transform(body())
                .to("stream:file?fileName={{out}}")
                .to("myBean");

        //from rest call to bean with split
        from(startEndpoint)
                .setHeader("myHeader", constant("all"))
                .split(body())
                //.aggregate(header("myHeader"), aggregator)
                //.completionSize(50)
                .to("myBean");
    }

 }