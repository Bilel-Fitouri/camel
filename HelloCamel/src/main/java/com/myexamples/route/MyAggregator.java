package com.myexamples.route;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

/**
 * Created by bfitouri on 12/02/16.
 */
@Component
public class MyAggregator implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange exchange, Exchange exchange1) {
        exchange1.getIn().setBody("name is " + exchange1.getIn().getBody());
        return exchange1;
    }
}
