package objects;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by AZeaage on 3/30/2017.
 */

public class SalesOrderDetails implements Serializable {
    private int RecordID;
    private String UOM;
    private int Qty;
    private double Price;
    private double LineTotal;
    private Date RequirementDate;
    private Products products;

    public SalesOrderDetails(int recordID, String UOM, int qty, double price, double lineTotal, Date requirementDate, Products products) {
        RecordID = recordID;
        this.UOM = UOM;
        Qty = qty;
        Price = price;
        LineTotal = lineTotal;
        RequirementDate = requirementDate;
        this.products=products;
    }

    public SalesOrderDetails() {
        RecordID=1213;
        UOM="ghug";
        Qty=3;
        Price=111;
        LineTotal=7547;
        RequirementDate=null;
    }

    public int getRecordID() {
        return RecordID;
    }

    public void setRecordID(int recordID) {
        RecordID = recordID;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getLineTotal() {
        return LineTotal;
    }

    public void setLineTotal(double lineTotal) {
        LineTotal = lineTotal;
    }

    public Date getRequirementDate() {
        return RequirementDate;
    }

    public void setRequirementDate(Date requirementDate) {
        RequirementDate = requirementDate;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "SalesOrderDetails{" +

                "RecordID=" + RecordID +
                ", UOM='" + UOM + '\'' +
                ", Qty=" + Qty +
                ", Price=" + Price +
                ", LineTotal=" + LineTotal +
                ", RequirementDate=" + RequirementDate +
                '}';
    }
}
