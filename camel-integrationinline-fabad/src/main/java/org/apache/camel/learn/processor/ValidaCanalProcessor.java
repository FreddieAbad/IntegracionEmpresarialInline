package org.apache.camel.learn.processor;

import org.apache.camel.Exchange;
import org.apache.camel.learn.model.ClientInfo;

public class ValidaCanalProcessor implements org.apache.camel.Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ClientInfo persona = exchange.getIn().getBody(ClientInfo.class);
        exchange.getMessage().setHeader("canal", persona.getCanal());
    }
}
