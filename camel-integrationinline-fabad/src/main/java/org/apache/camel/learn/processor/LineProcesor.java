package org.apache.camel.learn.processor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.learn.model.LineModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LineProcesor implements Processor {
    public int contadorError=0;
    public int contadorOk=0;
    private static final Logger LOG = LoggerFactory.getLogger(LineProcesor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        LineModel lineModel = (LineModel) exchange.getIn().getBody();
        boolean pagos = isAnyPayLessOrEqualZero(lineModel);
        boolean bills = isAnyBillLessOrEqualZero(lineModel);
        exchange.getMessage().setHeader("pagos", pagos);
        exchange.getMessage().setHeader("bills", bills);
        if (!pagos && !bills) {
            LOG.info(" Linea :" + lineModel.getID() + " cumple condicion valores de factura a pagar y pagados mayores a 0");
            contadorOk+=1;
        } else{
            LOG.info(" Linea :" + lineModel.getID() + " no cumple condicion valores de factura a pagar y pagados mayores a 0");
            contadorError+=1;
        }
        exchange.setProperty("contadorOk", contadorOk);
        exchange.setProperty("contadorError", contadorError);
        exchange.setProperty("actualLine", lineModel.getID());
    }
    public static boolean isAnyPayLessOrEqualZero(LineModel lineModel) {
        if (Integer.parseInt(lineModel.getPAY_1()) <= 0 ||
                Integer.parseInt(lineModel.getPAY_22()) <= 0 ||
                Integer.parseInt(lineModel.getPAY_23()) <= 0 ||
                Integer.parseInt(lineModel.getPAY_24()) <= 0 ||
                Integer.parseInt(lineModel.getPAY_25()) <= 0 ||
                Integer.parseInt(lineModel.getPAY_26()) <= 0) {
            return true;
        }
        return false;
    }
    public static boolean isAnyBillLessOrEqualZero(LineModel lineModel) {
        if (Integer.parseInt(lineModel.getBILL_1()) <= 0 ||
                Integer.parseInt(lineModel.getBILL_2()) <= 0 ||
                Integer.parseInt(lineModel.getBILL_3()) <= 0 ||
                Integer.parseInt(lineModel.getBILL_4()) <= 0 ||
                Integer.parseInt(lineModel.getBILL_5()) <= 0 ||
                Integer.parseInt(lineModel.getBILL_6()) <= 0) {
            return true;
        }
        return false;
    }
}
