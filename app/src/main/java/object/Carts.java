package object;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

import object.*;

/**
 * Created by AZeaage on 4/2/2017.
 */

public class Carts implements Serializable {
    private int CartId;
    private double CartTotal;
    private boolean isConfirmed;
    private Date ConfirmationDate;
    private Date CreationDate;
    private CartDetails cartDetails;
    private object.Products[] products;


    public Carts(int cartId, double cartTotal, boolean isConfirmed, Date confirmationDate, Date creationDate) {
        CartId = cartId;
        CartTotal = cartTotal;
        this.isConfirmed = isConfirmed;
        ConfirmationDate = confirmationDate;
        CreationDate = creationDate;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }

    public double getCartTotal() {
        return CartTotal;
    }

    public void setCartTotal(double cartTotal) {
        CartTotal = cartTotal;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Date getConfirmationDate() {
        return ConfirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        ConfirmationDate = confirmationDate;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "CartId=" + CartId +
                ", CartTotal=" + CartTotal +
                ", isConfirmed=" + isConfirmed +
                ", ConfirmationDate=" + ConfirmationDate +
                ", CreationDate=" + CreationDate +
                ", cartDetails=" + cartDetails +
                ", products=" + Arrays.toString(products) +
                '}';
    }
}

