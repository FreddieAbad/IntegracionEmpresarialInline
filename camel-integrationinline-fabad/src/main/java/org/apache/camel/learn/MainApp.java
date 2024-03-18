package org.apache.camel.learn;
import org.apache.camel.learn.builder.MyRouteBuilder;
import org.apache.camel.main.Main;

public class MainApp {
    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.configure().addRoutesBuilder(new MyRouteBuilder());
        main.run(args);
    }
}