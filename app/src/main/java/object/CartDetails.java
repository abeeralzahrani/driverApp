package object;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by AZeaage on 4/2/2017.
 */

public class CartDetails implements Serializable {
    private int RecordID;
    private String UOM;
    private int Qty;
    private double Price;
    private double LineTotal;
    private Date RequiredDate;

    public CartDetails(int recordID, String UOM, int qty, double price, double lineTotal, Date requiredDate) {
        RecordID = recordID;
        this.UOM = UOM;
        Qty = qty;
        Price = price;
        LineTotal = lineTotal;
        RequiredDate = requiredDate;
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

    public Date getRequiredDate() {
        return RequiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        RequiredDate = requiredDate;
    }

    @Override
    public String toString() {
        return "CartDetails{" +
                "RecordID=" + RecordID +
                ", UOM='" + UOM + '\'' +
                ", Qty=" + Qty +
                ", Price=" + Price +
                ", LineTotal=" + LineTotal +
                ", RequiredDate=" + RequiredDate +
                '}';
    }
}
