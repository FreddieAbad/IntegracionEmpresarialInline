
package org.apache.camel.learn.builder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.learn.model.ClientInfo;
import org.apache.camel.learn.processor.ProcesaRespuestaProcessor;
import org.apache.camel.learn.processor.ServicioAProcessor;
import org.apache.camel.learn.processor.ServicioBProcessor;
import org.apache.camel.learn.processor.ValidaCanalProcessor;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.commons.io.IOUtils; // Importa la clase IOUtils de Commons IO
import java.io.ByteArrayInputStream; // Importa la clase ByteArrayInputStream de Java IO

import java.io.InputStream;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:80/receive_client_info?httpMethodRestrict=POST")
                .routeId("ValidaCanal")
                .unmarshal().json(JsonLibrary.Jackson, ClientInfo.class)
                .process(new ValidaCanalProcessor())
                .choice()
                    .when(header("canal").isEqualTo("online"))
                        .to("direct:servicioONLINE")
                    .when(header("canal").isEqualTo("offline"))
                        .to("direct:servicioOffLINE")
                    .otherwise()
                        .setBody(constant("{\"message\": \"Canal no permitido.\"}"))
                .end();

        from("jetty:http://0.0.0.0:80/users/online?httpMethodRestrict=GET")
                .routeId("OnlineService")
                .setHeader("canal").constant("online")
                .to("direct:servicioAC");

        from("jetty:http://0.0.0.0:80/users/offline?httpMethodRestrict=GET")
                .routeId("OfflineService")
                .setHeader("canal").constant("offline")
                .to("direct:servicioBD");


        from("direct:servicioONLINE")
                .routeId("ServicioONLINE")
                .log("**Enviando a Servicio A ONLINE:** ${body}")
                .process(new ServicioAProcessor())
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .to("http://service1:8080/users?bridgeEndpoint=true")
                .process(new ProcesaRespuestaProcessor())

                /*.process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // Convertir el cuerpo de byte[] a InputStream
                        byte[] responseBody = exchange.getMessage().getBody(byte[].class);
                        InputStream inputStream = new ByteArrayInputStream(responseBody);
                        exchange.getMessage().setBody(inputStream);
                    }
                })*/
                .unmarshal().json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .log("**Respuesta de Servicio A ONLINE:** ${body}")
                ;

        from("direct:servicioOffLINE")
                .routeId("ServicioOffLINE")
                .log("**Enviando a Servicio OffLINE:** ${body}")
                .process(new ServicioBProcessor())
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .to("http://service2:5000/users?bridgeEndpoint=true")
                    .process(new ProcesaRespuestaProcessor())
                    .unmarshal().json(JsonLibrary.Jackson)
                    .convertBodyTo(String.class)
                    .log("**Respuesta de Servicio A OffLINE:** ${body}");


        from("direct:servicioBD")
                .routeId("ServicioBD")
                .log("**Enviando a Servicio BD:** ${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("http://service2:5000/get_users?bridgeEndpoint=true")
                .process(new ProcesaRespuestaProcessor())
        ;

        from("direct:servicioAC")
                .routeId("ServicioAC")
                .log("**Enviando a Servicio AC:** ${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("http://service1:8080/get_users?bridgeEndpoint=true")
                .process(new ProcesaRespuestaProcessor())
        ;
    }
}
