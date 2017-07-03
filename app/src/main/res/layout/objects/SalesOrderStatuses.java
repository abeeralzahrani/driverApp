package objects;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by AZeaage on 3/30/2017.
 */

public class SalesOrderStatuses implements Serializable {
    private int StatusID;
    private int StatusCode;
    private String StatusDesc;
    private boolean isCancelled;
    private Date CancellationDate;
    private String CreatedBy;
    private Date CreationDate;
    private String LastModifiedBy;
    private Date LastModificationDate;

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int statusID) {
        StatusID = statusID;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
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

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
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

    @Override
    public String toString() {
        return "SalesOrderStatuses{" +
                "StatusID=" + StatusID +
                ", StatusCode=" + StatusCode +
                ", StatusDesc='" + StatusDesc + '\'' +
                ", isCancelled=" + isCancelled +
                ", CancellationDate=" + CancellationDate +
                ", CreatedBy='" + CreatedBy + '\'' +
                ", CreationDate=" + CreationDate +
                ", LastModifiedBy='" + LastModifiedBy + '\'' +
                ", LastModificationDate=" + LastModificationDate +
                '}';
    }
}
