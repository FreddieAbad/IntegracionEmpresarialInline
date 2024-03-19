package org.apache.camel.learn.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.learn.model.ClientInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicioBProcessor implements org.apache.camel.Processor {
    private static final Logger LOG = LoggerFactory.getLogger(ServicioBProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        ClientInfo clientInfo = exchange.getIn().getBody(ClientInfo.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(clientInfo);
        exchange.getIn().setBody(jsonBody);
    }
}
