
package org.apache.camel.learn.builder;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.learn.model.LineModel;
import org.apache.camel.learn.processor.CountProcessor;
import org.apache.camel.learn.processor.FinProcessor;
import org.apache.camel.learn.processor.InsertProcesor;
import org.apache.camel.learn.processor.LineProcesor;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.builder.PredicateBuilder;
import java.util.Properties;

public class MyRouteBuilder extends RouteBuilder {

    DataFormat bindy = new BindyCsvDataFormat(LineModel.class);
    Properties properties = new Properties();



    @Override
    public void configure() throws Exception {
        properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

        String ruta = properties.getProperty("ruta");

        String filename = properties.getProperty("filename");
        String databaseJDBCConnection = properties.getProperty("database.jdbc.connection");
        String databaseUserConnection = properties.getProperty("database.user");
        String databasePasswordConnection = properties.getProperty("database.password");

        String uri= "file:"+ruta+"?noop=True&fileName="+filename+"&delay=300000";
        //"file:src/datos?noop=True&fileName=cardsclients.csv&delay=3000"

        from(uri)
                .setHeader("rutaFile", constant(ruta + "/"+filename))
                .to("direct:countRecords")
                .log("INICIO DE PROCESAMIENTO")
                .split().tokenize("\n")
                .filter(body().isNotNull())
                .unmarshal(bindy)
                .to("direct:validateLine")
                .to("direct:validateFinProcess");

        from("direct:validateLine")
                .process(new LineProcesor())
                .choice()
                .when(PredicateBuilder.and(header("pagos").isEqualTo(false), header("bills").isEqualTo(false)))
                .to("direct:processInsert")
                .end()
                .end();

        from("direct:processInsert")
                .process(new InsertProcesor(databaseJDBCConnection, databaseUserConnection, databasePasswordConnection));

        from("direct:countRecords")
                .process(new CountProcessor());

        from("direct:validateFinProcess")
                .process(new FinProcessor());

    }


}
