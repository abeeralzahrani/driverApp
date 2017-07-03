package object;

import java.util.ArrayList;

/**
 * Created by AZeaage on 5/9/2017.
 */

public class DeliverPlace {
    private String Region ;
    private ArrayList<String> cities ;

    public DeliverPlace() {
        Region="";
        cities=new ArrayList<>();
    }

    public DeliverPlace(String region) {
        Region = region;
        cities=new ArrayList<>();

    }

    public void setCity(String city){
        cities.add(city);
    }
    public String getCity(int pos){
        return cities.get(pos);
    }


    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    @Override
    public String toString() {
        return "DeliverPlace{" +
                "Region='" + Region + '\'' +
                ", cities=" + cities +
                '}';
    }
}
