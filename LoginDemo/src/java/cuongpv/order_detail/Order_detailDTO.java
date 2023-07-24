/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.order_detail;

import java.io.Serializable;

/**
 *
 * @author datnt
 */
public class Order_detailDTO implements Serializable{
    private int id;
    private String sku;
    private String orderId;
    private int quantity;

    public Order_detailDTO(int id, String sku, String orderId, int quantity) {
        this.id = id;
        this.sku = sku;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
