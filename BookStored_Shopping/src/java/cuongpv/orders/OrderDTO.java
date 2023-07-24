/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.orders;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author datnt
 */
public class OrderDTO implements Serializable {
    private int id;
    private Date date;
    private long total;

    public OrderDTO(int id, Date date, long total) {
        this.id = id;
        this.date = date;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public long getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    
    
}
