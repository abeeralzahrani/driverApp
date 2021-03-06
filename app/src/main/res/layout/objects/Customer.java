package objects;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by AZeaage on 3/26/2017.
 */

public class Customer implements Serializable {
    private String full_name ;
    private String email ;
    private String phone ;
    private String password ;
    private LatLng coordinates;
    private String address;

    public Customer(String full_name, String phone, String address) {
        this.full_name = full_name;
        this.phone = phone;
        this.address = address;
    }

    public Customer() {
        full_name="";
        this.email = "";
        this.phone = "";
        this.password = "";
        this.coordinates = null;
    }

    public Customer(String full_name, String email, String phone, String password, LatLng coordinates) {
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.coordinates = coordinates;
    }

    public Customer(String full_name,  String email, String phone, String password) {
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getLatLng() {
        return coordinates;
    }

    public void setLatLng(LatLng coordinates) {
        this.coordinates = coordinates;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
