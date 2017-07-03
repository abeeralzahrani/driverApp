package objects;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by AZeaage on 4/2/2017.
 */

public class Branches implements Serializable {
    private int BranchCode;
    private String BranchName;
    private String BranchAddress;
    private String BranchCity;
  private LatLng coordinates ;

    public int getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(int branchCode) {
        BranchCode = branchCode;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getBranchAddress() {
        return BranchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        BranchAddress = branchAddress;
    }

    public String getBranchCity() {
        return BranchCity;
    }

    public void setBranchCity(String branchCity) {
        BranchCity = branchCity;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Branches{" +
                "BranchCode=" + BranchCode +
                ", BranchName='" + BranchName + '\'' +
                ", BranchAddress='" + BranchAddress + '\'' +
                ", BranchCity='" + BranchCity + '\'' +
               ", coordinates=" + coordinates +
                '}';
    }
}

