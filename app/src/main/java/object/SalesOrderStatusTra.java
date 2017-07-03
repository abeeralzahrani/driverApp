package object;

import java.io.Serializable;
import java.sql.Date;

import object.*;

/**
 * Created by AZeaage on 3/30/2017.
 */

public class SalesOrderStatusTra implements Serializable {
    private int RecordID;
    private String Comments;
    private Date StatusDateTime;
    private String StatusUpdatedBy;
    private SalesOrderStatuses salesOrderStatuses;

    public SalesOrderStatuses getSalesOrderStatuses() {
        return salesOrderStatuses;
    }

    public void setSalesOrderStatuses(SalesOrderStatuses salesOrderStatuses) {
        this.salesOrderStatuses = salesOrderStatuses;
    }

    public int getRecordID() {
        return RecordID;
    }

    public void setRecordID(int recordID) {
        RecordID = recordID;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public Date getStatusDateTime() {
        return StatusDateTime;
    }

    public void setStatusDateTime(Date statusDateTime) {
        StatusDateTime = statusDateTime;
    }

    public String getStatusUpdatedBy() {
        return StatusUpdatedBy;
    }

    public void setStatusUpdatedBy(String statusUpdatedBy) {
        StatusUpdatedBy = statusUpdatedBy;
    }

    @Override
    public String toString() {
        return "SalesOrderStatusTra{" +
                "RecordID=" + RecordID +
                ", Comments='" + Comments + '\'' +
                ", StatusDateTime=" + StatusDateTime +
                ", StatusUpdatedBy='" + StatusUpdatedBy + '\'' +
                ", salesOrderStatuses=" + salesOrderStatuses +
                '}';
    }
}
