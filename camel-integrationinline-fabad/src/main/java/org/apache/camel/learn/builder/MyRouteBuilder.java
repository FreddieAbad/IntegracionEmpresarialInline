
package org.apache.camel.learn.builder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.learn.model.ClientInfo;
import org.apache.camel.model.dataformat.JsonLibrary;



public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:80/receive_client_info?httpMethodRestrict=POST")
                .routeId("ValidaCanal")
                .unmarshal().json(JsonLibrary.Jackson, ClientInfo.class)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        ClientInfo persona = exchange.getIn().getBody(ClientInfo.class);
                        exchange.getMessage().setHeader("canal", persona.getCanal());
//                        System.out.println(persona.toString());
                    }
                })
                .choice()
                .when(header("canal").isEqualTo("online"))
                .to("direct:servicioA")
                .otherwise()
                .to("direct:servicioB")
                .end();

        from("direct:servicioA")
                .routeId("ServicioA")
                .log("**Enviando a Servicio A:** ${body}");

        from("direct:servicioB")
                .routeId("ServicioB")
                .log("**Enviando a Servicio B:** ${body}");
    }
}
