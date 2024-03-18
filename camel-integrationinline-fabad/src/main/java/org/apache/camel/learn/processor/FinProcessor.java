package org.apache.camel.learn.processor;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class FinProcessor implements org.apache.camel.Processor {
    private static final Logger LOG = LoggerFactory.getLogger(LineProcesor.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        String recordCount = exchange.getProperty("recordCount", String.class);
        String actualLine = exchange.getProperty("actualLine", String.class);
        int totalLineas = Integer.parseInt(recordCount)-1;
        if(totalLineas == Integer.parseInt(actualLine)){
            LOG.info("###########################################################");
            LOG.info("########################  RESUMEN  ########################");
            LOG.info("### TOTAL LINEAS: "+totalLineas+" ###");
            LOG.info("### LINEAS VALIDADAS: "+exchange.getProperty("contadorOk")+" OK y "+exchange.getProperty("contadorError")+" ERROR ###");
            LOG.info("###########################################################");
            exchange.setProperty("contadorOk",0);
            exchange.setProperty("contadorError",0);
        }
    }
}
