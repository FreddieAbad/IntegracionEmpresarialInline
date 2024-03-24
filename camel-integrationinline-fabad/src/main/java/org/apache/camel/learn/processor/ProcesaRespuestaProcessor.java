package org.apache.camel.learn.processor;

import org.apache.camel.Exchange;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ProcesaRespuestaProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        // Convertir el cuerpo de byte[] a InputStream
        byte[] responseBody = exchange.getMessage().getBody(byte[].class);
        InputStream inputStream = new ByteArrayInputStream(responseBody);
        exchange.getMessage().setBody(inputStream);
    }
}
