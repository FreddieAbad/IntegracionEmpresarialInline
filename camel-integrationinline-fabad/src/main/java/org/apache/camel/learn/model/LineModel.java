package org.apache.camel.learn.model;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",")
public class LineModel {
    @DataField(pos = 1)
    private String ID;
    @DataField(pos = 2)
    private String LIMIT_BAL;
    @DataField(pos = 3)
    private String SEX;
    @DataField(pos = 4)
    private String EDUCATION;
    @DataField(pos = 5)
    private String MARRIAGE;
    @DataField(pos = 6)
    private String AGE;
    @DataField(pos = 7)
    private String PAY_0;
    @DataField(pos = 8)
    private String PAY_2;
    @DataField(pos = 9)
    private String PAY_3;
    @DataField(pos = 10)
    private String PAY_4;
    @DataField(pos = 11)
    private String PAY_5;
    @DataField(pos = 12)
    private String PAY_6;
    @DataField(pos = 13)
    private String BILL_1;
    @DataField(pos = 14)
    private String BILL_2;
    @DataField(pos = 15)
    private String BILL_3;
    @DataField(pos = 16)
    private String BILL_4;
    @DataField(pos = 17)
    private String BILL_5;
    @DataField(pos = 18)
    private String BILL_6;
    @DataField(pos = 19)
    private String PAY_1;
    @DataField(pos = 20)
    private String PAY_22;
    @DataField(pos = 21)
    private String PAY_23;
    @DataField(pos = 22)
    private String PAY_24;
    @DataField(pos = 23)
    private String PAY_25;
    @DataField(pos = 24)
    private String PAY_26;
    @DataField(pos = 25)
    private String default_payment_next_month;

    @Override
    public String toString() {
        return "Linea{" +
                "ID=" + ID +
                ", LIMIT_BAL=" + LIMIT_BAL +
                ", SEX=" + SEX +
                ", EDUCATION=" + EDUCATION +
                ", MARRIAGE=" + MARRIAGE +
                ", AGE=" + AGE +
                ", PAY_0=" + PAY_0 +
                ", PAY_2=" + PAY_2 +
                ", PAY_3=" + PAY_3 +
                ", PAY_4=" + PAY_4 +
                ", PAY_5=" + PAY_5 +
                ", PAY_6=" + PAY_6 +
                ", BILL_1=" + BILL_1 +
                ", BILL_2=" + BILL_2 +
                ", BILL_3=" + BILL_3 +
                ", BILL_4=" + BILL_4 +
                ", BILL_5=" + BILL_5 +
                ", BILL_6=" + BILL_6 +
                ", PAY_1=" + PAY_1 +
                ", PAY_22=" + PAY_22 +
                ", PAY_23=" + PAY_23 +
                ", PAY_24=" + PAY_24 +
                ", PAY_25=" + PAY_25 +
                ", PAY_26=" + PAY_26 +
                ", default_payment_next_month=" + default_payment_next_month +
                '}';
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getLIMIT_BAL() {
        return LIMIT_BAL;
    }

    public void setLIMIT_BAL(String lIMIT_BAL) {
        LIMIT_BAL = lIMIT_BAL;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String sEX) {
        SEX = sEX;
    }

    public String getEDUCATION() {
        return EDUCATION;
    }

    public void setEDUCATION(String eDUCATION) {
        EDUCATION = eDUCATION;
    }

    public String getMARRIAGE() {
        return MARRIAGE;
    }

    public void setMARRIAGE(String mARRIAGE) {
        MARRIAGE = mARRIAGE;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String aGE) {
        AGE = aGE;
    }

    public String getPAY_0() {
        return PAY_0;
    }

    public void setPAY_0(String pAY_0) {
        PAY_0 = pAY_0;
    }

    public String getPAY_2() {
        return PAY_2;
    }

    public void setPAY_2(String pAY_2) {
        PAY_2 = pAY_2;
    }

    public String getPAY_3() {
        return PAY_3;
    }

    public void setPAY_3(String pAY_3) {
        PAY_3 = pAY_3;
    }

    public String getPAY_4() {
        return PAY_4;
    }

    public void setPAY_4(String pAY_4) {
        PAY_4 = pAY_4;
    }

    public String getPAY_5() {
        return PAY_5;
    }

    public void setPAY_5(String pAY_5) {
        PAY_5 = pAY_5;
    }

    public String getPAY_6() {
        return PAY_6;
    }

    public void setPAY_6(String pAY_6) {
        PAY_6 = pAY_6;
    }

    public String getBILL_1() {
        return BILL_1;
    }

    public void setBILL_1(String bILL_1) {
        BILL_1 = bILL_1;
    }

    public String getBILL_2() {
        return BILL_2;
    }

    public void setBILL_2(String bILL_2) {
        BILL_2 = bILL_2;
    }

    public String getBILL_3() {
        return BILL_3;
    }

    public void setBILL_3(String bILL_3) {
        BILL_3 = bILL_3;
    }

    public String getBILL_4() {
        return BILL_4;
    }

    public void setBILL_4(String bILL_4) {
        BILL_4 = bILL_4;
    }

    public String getBILL_5() {
        return BILL_5;
    }

    public void setBILL_5(String bILL_5) {
        BILL_5 = bILL_5;
    }

    public String getBILL_6() {
        return BILL_6;
    }

    public void setBILL_6(String bILL_6) {
        BILL_6 = bILL_6;
    }

    public String getPAY_1() {
        return PAY_1;
    }

    public void setPAY_1(String pAY_1) {
        PAY_1 = pAY_1;
    }

    public String getPAY_22() {
        return PAY_22;
    }

    public void setPAY_22(String pAY_22) {
        PAY_22 = pAY_22;
    }

    public String getPAY_23() {
        return PAY_23;
    }

    public void setPAY_23(String pAY_23) {
        PAY_23 = pAY_23;
    }

    public String getPAY_24() {
        return PAY_24;
    }

    public void setPAY_24(String pAY_24) {
        PAY_24 = pAY_24;
    }

    public String getPAY_25() {
        return PAY_25;
    }

    public void setPAY_25(String pAY_25) {
        PAY_25 = pAY_25;
    }

    public String getPAY_26() {
        return PAY_26;
    }

    public void setPAY_26(String pAY_26) {
        PAY_26 = pAY_26;
    }

    public String getDefault_payment_next_month() {
        return default_payment_next_month;
    }

    public void setDefault_payment_next_month(String default_payment_next_month) {
        this.default_payment_next_month = default_payment_next_month;
    }
}
