package object;

//import com.google.android.gms.maps.model.LatLng;

import android.support.annotation.NonNull;
import android.text.SpannableString;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by AZeaage on 3/30/2017.
 */

public class SalesOrders implements Serializable , Comparable<SalesOrders> {
    private int OrderId;
    private Date OrderDate;
    private String ShippingAddress;
    private String ShippingCity;
    private LatLng locationCoordinate ;
    private String Latitude;
    private String Longitude;
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
    private String driverID;
    private String docStatues;
    private sortSalesOrders SortSalesOrders;

    public String getDriverID() {
        return driverID;
    }
//Integer.parseInt(InvoiceNum),docStatus,Latitude,Longitude, customer
    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public SalesOrders(int SAPInvoiceNo,String docStatues,String latitude, String longitude,Customer customer ) {
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.SAPInvoiceNo = SAPInvoiceNo;
        this.customer = customer;
        this.docStatues = docStatues;
        SortSalesOrders = new sortSalesOrders(0,0.0,0);
    }

    public SalesOrders(int SAPInvoiceNo, String docStatues, Customer customer) {
        this.SAPInvoiceNo = SAPInvoiceNo;
        this.customer = customer;
        OrderId = 0;
        OrderDate = null;
        ShippingAddress = "";
        ShippingCity = "";
        OrderTotal = 0.0;
        CompletionCode = "";
        CreatedBy = null;
        driverID="no driver assign ";
        this.docStatues=docStatues;
        SortSalesOrders = new sortSalesOrders(0,0.0,0);
    }

    public sortSalesOrders getSortSalesOrders() {
        return SortSalesOrders;
    }

    public void setSortSalesOrders(sortSalesOrders sortSalesOrders) {
        SortSalesOrders = sortSalesOrders;
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
        OrderTotal = orderTotal;
        this.SAPInvoiceNo = SAPInvoiceNo;
        CompletionCode = completionCode;
        this.isCancelled = isCancelled;
        CreatedBy = createdBy;

    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getDocStatues() {
        return docStatues;
    }

    public void setDocStatues(String docStatues) {
        this.docStatues = docStatues;
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

               " locationCoordinate=" + locationCoordinate +
                ", SAPInvoiceNo=" + SAPInvoiceNo +
                ", isCancelled=" + isCancelled +
                ", customer=" + customer +
                ",Distance"+ SortSalesOrders.getDistance()+
                ",Duration" +SortSalesOrders.getDuration()+

                '}';
    }

    @Override
    public int compareTo(@NonNull SalesOrders o) {
        int result=0;
        if(SortSalesOrders.getDistance() < o.getSortSalesOrders().getDistance()){
            result=-1;
        }
        else if(SortSalesOrders.getDistance()>  o.getSortSalesOrders().getDistance()){
            result=1;
        }
        return result;
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
