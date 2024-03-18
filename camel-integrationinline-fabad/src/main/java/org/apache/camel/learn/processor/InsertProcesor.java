package org.apache.camel.learn.processor;

import javax.sql.DataSource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.learn.model.LineModel;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertProcesor implements Processor {

    private DataSource dataSource;

    public InsertProcesor(String databaseConnection, String databaseUserConnection, String databasePasswordConnection) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername(databaseUserConnection);
        ds.setPassword(databasePasswordConnection);
        ds.setUrl(databaseConnection);
        this.dataSource = ds;
    }
    @Override
    public void process(Exchange exchange) throws Exception {
        LineModel lineModel = (LineModel) exchange.getIn().getBody();
        String sql = "INSERT INTO linea (ID, LIMIT_BAL, SEX, EDUCATION, MARRIAGE, AGE, PAY_0, PAY_2, PAY_3, PAY_4, PAY_5, PAY_6, BILL_1, BILL_2, BILL_3, BILL_4, BILL_5, BILL_6, PAY_1, PAY_22, PAY_23, PAY_24, PAY_25, PAY_26, default_payment_next_month) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lineModel.getID());
            statement.setString(2, lineModel.getLIMIT_BAL());
            statement.setString(3, lineModel.getSEX());
            statement.setString(4, lineModel.getEDUCATION());
            statement.setString(5, lineModel.getMARRIAGE());
            statement.setString(6, lineModel.getAGE());
            statement.setString(7, lineModel.getPAY_0());
            statement.setString(8, lineModel.getPAY_2());
            statement.setString(9, lineModel.getPAY_3());
            statement.setString(10, lineModel.getPAY_4());
            statement.setString(11, lineModel.getPAY_5());
            statement.setString(12, lineModel.getPAY_6());
            statement.setString(13, lineModel.getBILL_1());
            statement.setString(14, lineModel.getBILL_2());
            statement.setString(15, lineModel.getBILL_3());
            statement.setString(16, lineModel.getBILL_4());
            statement.setString(17, lineModel.getBILL_5());
            statement.setString(18, lineModel.getBILL_6());
            statement.setString(19, lineModel.getPAY_1());
            statement.setString(20, lineModel.getPAY_22());
            statement.setString(21, lineModel.getPAY_23());
            statement.setString(22, lineModel.getPAY_24());
            statement.setString(23, lineModel.getPAY_25());
            statement.setString(24, lineModel.getPAY_26());
            statement.setString(25, lineModel.getDefault_payment_next_month());
            statement.executeUpdate();
        }
    }
}
