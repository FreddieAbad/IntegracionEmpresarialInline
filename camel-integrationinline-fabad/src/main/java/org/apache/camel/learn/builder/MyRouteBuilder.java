
package org.apache.camel.learn.builder;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.learn.model.ClientInfo;
import org.apache.camel.learn.processor.ServicioAProcessor;
import org.apache.camel.learn.processor.ServicioBProcessor;
import org.apache.camel.learn.processor.ValidaCanalProcessor;
import org.apache.camel.model.dataformat.JsonLibrary;
public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:81/receive_client_info?httpMethodRestrict=POST")
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

        from("jetty:http://0.0.0.0:81/users/online?httpMethodRestrict=GET")
                .routeId("OnlineService")
                .setHeader("canal").constant("online")
                .to("direct:servicioAC");

        from("jetty:http://0.0.0.0:81/users/offline?httpMethodRestrict=GET")
                .routeId("OfflineService")
                .setHeader("canal").constant("offline")
                .to("direct:servicioBD");


        from("direct:servicioONLINE")
                .routeId("ServicioONLINE")
                .log("**Enviando a Servicio A ONLINE:** ${body}")
                .process(new ServicioAProcessor())
                .doTry()
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .to("http://service1:8080/users?bridgeEndpoint=true")
                    .unmarshal().json(JsonLibrary.Jackson)
                    .convertBodyTo(String.class)
                    .log("**Respuesta de Servicio A ONLINE:** ${body}")
                .doCatch(Exception.class)
                    .setBody(constant("{\"error\": \"Ocurrió un error al acceder al servicio OnLINE Service1.\"}"))
                    .log("**Ocurrió un error al acceder al servicio ONLINE - Service1.:** ${exception.message}")
                .endDoTry();

        from("direct:servicioOffLINE")
                .routeId("ServicioOffLINE")
                .log("**Enviando a Servicio OffLINE:** ${body}")
                .process(new ServicioBProcessor())
                .doTry()
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .to("http://service2:5000/users?bridgeEndpoint=true")
                    .unmarshal().json(JsonLibrary.Jackson)
                    .convertBodyTo(String.class)
                    .log("**Respuesta de Servicio A OffLINE:** ${body}")
                .doCatch(Exception.class)
                .setBody(constant("{\"error\": \"Ocurrió un error al acceder al servicio OffLINE Service1.\"}"))
                .log("**Ocurrió un error al acceder al servicio OffLINE - Service1.:** ${exception.message}")
                .endDoTry();

        from("direct:servicioBD")
                .routeId("ServicioBD")
                .log("**Enviando a Servicio BD:** ${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("http://service2:5000/get_users?bridgeEndpoint=true");

        from("direct:servicioAC")
                .routeId("ServicioAC")
                .log("**Enviando a Servicio AC:** ${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("http://service1:8080/get_users?bridgeEndpoint=true");
    }
}
