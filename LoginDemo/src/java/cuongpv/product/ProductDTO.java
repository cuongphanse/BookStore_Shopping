/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.product;

import java.io.Serializable;

/**
 *
 * @author datnt
 */
public class ProductDTO implements Serializable{
    private String itemId;
    private String itemName;
    private int itemPrice;
    private int quantity;
    private boolean isAvailable;

    public ProductDTO() {
    }

    public ProductDTO(String itemId, String itemName, int itemPrice, int quantity, boolean isAvailable) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    
}
