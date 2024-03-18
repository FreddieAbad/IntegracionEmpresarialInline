package org.apache.camel.learn.processor;

import org.apache.camel.Exchange;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CountProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String ruta = exchange.getIn().getHeader("rutaFile", String.class);
        File file = new File(ruta);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int recordCount = 0;
        while (reader.readLine() != null) {
            recordCount++;
        }
        reader.close();
        exchange.setProperty("recordCount", recordCount);
    }
}
