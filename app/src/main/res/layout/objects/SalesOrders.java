package objects;

//import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by AZeaage on 3/30/2017.
 */

public class SalesOrders implements Serializable {
    private int OrderId;
    private Date OrderDate;
    private String ShippingAddress;
    private String ShippingCity;
    private LatLng locationCoordinate ;
    private double OrderTotal;
    private int SAPInvoiceNo;
    private String CompletionCode;
    private boolean isCancelled;
    private Date CancellationDate;
    private String CreatedBy;
    private String LastModifiedBy;
    private Date LastModificationDate;
    private Customer customer;
    private ArrayList<SalesOrderDetails> salesOrderDetails;
    private SalesOrderStatusTra salesOrderStatusTra;
    private int mData;
    boolean selected =false;

    public SalesOrders(int SAPInvoiceNo, Customer customer) {
        this.SAPInvoiceNo = SAPInvoiceNo;
        this.customer = customer;
    }

    public ArrayList<SalesOrderDetails> getSalesOrderDetails() {
        return salesOrderDetails;
    }

    public void setSalesOrderDetails(ArrayList<SalesOrderDetails> salesOrderDetails) {
        this.salesOrderDetails = salesOrderDetails;
    }

    public SalesOrderStatusTra getSalesOrderStatusTra() {
        return salesOrderStatusTra;
    }

    public void setSalesOrderStatusTra(SalesOrderStatusTra salesOrderStatusTra) {
        this.salesOrderStatusTra = salesOrderStatusTra;
    }

    public SalesOrders(int orderId, Date orderDate, String shippingAddress, String shippingCity,
                       LatLng location, double orderTotal, int SAPInvoiceNo, String completionCode, boolean isCancelled, String createdBy
    ) {
        OrderId = orderId;
        OrderDate = orderDate;
        ShippingAddress = shippingAddress;
        ShippingCity = shippingCity;
        this.locationCoordinate=locationCoordinate;
        OrderTotal = orderTotal;
        this.SAPInvoiceNo = SAPInvoiceNo;
        CompletionCode = completionCode;
        this.isCancelled = isCancelled;
        CreatedBy = createdBy;

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getSAPInvoiceNo() {
        return SAPInvoiceNo;
    }

    public void setSAPInvoiceNo(int SAPInvoiceNo) {
        this.SAPInvoiceNo = SAPInvoiceNo;
    }

    public String getCompletionCode() {
        return CompletionCode;
    }

    public void setCompletionCode(String completionCode) {
        CompletionCode = completionCode;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return ShippingCity;
    }

    public void setShippingCity(String shippingCity) {
        ShippingCity = shippingCity;
    }

 public LatLng getLocationCoordinate() {
        return locationCoordinate;
    }

    public void setLocationCoordinate(LatLng locationCoordinate) {
        this.locationCoordinate = locationCoordinate;
    }

    public double getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        OrderTotal = orderTotal;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public Date getCancellationDate() {
        return CancellationDate;
    }

    public void setCancellationDate(Date cancellationDate) {
        CancellationDate = cancellationDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getLastModifiedBy() {
        return LastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        LastModifiedBy = lastModifiedBy;
    }

    public Date getLastModificationDate() {
        return LastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        LastModificationDate = lastModificationDate;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    @Override
    public String toString() {
        return "SalesOrders{" +
                "OrderId=" + OrderId +
                ", OrderDate=" + OrderDate +
                ", ShippingAddress='" + ShippingAddress + '\'' +
                ", ShippingCity='" + ShippingCity + '\'' +
               ", locationCoordinate=" + locationCoordinate +
                ", OrderTotal=" + OrderTotal +
                ", SAPInvoiceNo=" + SAPInvoiceNo +
                ", CompletionCode='" + CompletionCode + '\'' +
                ", isCancelled=" + isCancelled +
                ", CancellationDate=" + CancellationDate +
                ", CreatedBy='" + CreatedBy + '\'' +
                ", LastModifiedBy='" + LastModifiedBy + '\'' +
                ", LastModificationDate=" + LastModificationDate +
                ", customer=" + customer +
                ", salesOrderDetails=" + salesOrderDetails +
                ", salesOrderStatusTra=" + salesOrderStatusTra +
                ", mData=" + mData +
                '}';
    }
/*
  @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public static final Creator<SalesOrders> CREATOR = new Creator<SalesOrders>() {
        @Override
        public SalesOrders createFromParcel(Parcel in) {
            return new SalesOrders(in);
        }

        @Override
        public SalesOrders[] newArray(int size) {
            return new SalesOrders[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private SalesOrders(Parcel in) {
        mData = in.readInt();
    }*/
}
