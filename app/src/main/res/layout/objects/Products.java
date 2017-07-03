package objects;

import java.io.Serializable;

/**
 * Created by AZeaage on 4/2/2017.
 */

public class Products implements Serializable {
    private int ProductCode;
    private String ProductName_Ar;
    private String ProductName_En;
    private String ProductGroup;//EX : ورق جدران , باركية
    private String ProductType ;
    private double Price;
    private boolean isActive;

    public Products(int productCode, String productName_Ar, String productName_En, String productGroup, String productType, double price, boolean isActive) {
        ProductCode = productCode;
        ProductName_Ar = productName_Ar;
        ProductName_En = productName_En;
        ProductGroup = productGroup;
        ProductType = productType;
        Price = price;
        this.isActive = isActive;
    }

    public int getProductCode() {
        return ProductCode;
    }

    public void setProductCode(int productCode) {
        ProductCode = productCode;
    }

    public String getProductName_Ar() {
        return ProductName_Ar;
    }

    public void setProductName_Ar(String productName_Ar) {
        ProductName_Ar = productName_Ar;
    }

    public String getProductName_En() {
        return ProductName_En;
    }

    public void setProductName_En(String productName_En) {
        ProductName_En = productName_En;
    }

    public String getProductGroup() {
        return ProductGroup;
    }

    public void setProductGroup(String productGroup) {
        ProductGroup = productGroup;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Products{" +
                "ProductCode=" + ProductCode +
                ", ProductName_Ar='" + ProductName_Ar + '\'' +
                ", ProductName_En='" + ProductName_En + '\'' +
                ", ProductGroup='" + ProductGroup + '\'' +
                ", ProductType='" + ProductType + '\'' +
                ", Price=" + Price +
                ", isActive=" + isActive +
                '}';
    }
}

