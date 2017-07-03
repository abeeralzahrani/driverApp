package object;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by AZeaage on 6/11/2017.
 */

public class sortSalesOrders implements Comparable<sortSalesOrders>{
    private int duration;
    private Double distance;
    private int rank;


    public sortSalesOrders(int duration, Double distance, int rank) {
        this.duration = duration;
        this.distance = distance;
        this.rank = rank;
    }



    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "sortSalesOrders{" +
                "duration=" + duration +
                ", distance=" + distance +
                ", rank=" + rank +
                '}';
    }

  @Override
    public int compareTo(@NonNull sortSalesOrders o) {
        int result=0;
        if(this.distance < o.getDistance()){
            result=-1;
        }
       else if(this.distance> o.getDistance()){
            result=1;
        }
        return result;
    }
}
