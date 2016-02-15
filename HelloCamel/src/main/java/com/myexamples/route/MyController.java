package com.myexamples.route;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bfitouri on 12/02/16.
 */
@RestController
public class MyController {

    @EndpointInject(uri = "direct://startSomeThing")
    private ProducerTemplate producerTemplate;

    @RequestMapping(value = "/v1/post", method = RequestMethod.POST)
    public void doSomeThing(@RequestParam String a, @RequestParam String b, @RequestParam String c){
        List<String> list = Arrays.asList(a,b,c);
        this.producerTemplate.sendBody(list);
    }

    @RequestMapping(value = "/v1/get", method = RequestMethod.GET)
    public String getSomeThing(){
        return "ping pong";
    }

}
