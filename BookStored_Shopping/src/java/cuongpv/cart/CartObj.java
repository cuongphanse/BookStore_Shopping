/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.cart;

import cuongpv.product.ProductDAO;
import cuongpv.product.ProductDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author datnt
 */
public class CartObj implements Serializable {

    private Map<String, ProductDTO> items;
    //call model
    private ProductDAO dao = new ProductDAO();
    private List<ProductDTO> productListDTO;

    public CartObj() {
    }

    public Map<String, ProductDTO> getItems() {
        return items;
    }

    //behavior
    public boolean addItemToCart(String id, int quantity)
            throws SQLException, NamingException {
        boolean result = false;

        //1. check data validation//         
//        Check sản phẩm có tồn tại dưới DB hay không 
        if (id == null) {
            return result;
        }

        if (id.trim().isEmpty()) {
            return result;
        }

        if (quantity <= 0) {
            return result;
        }

        //2. check existed cart. 
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        //3. check exsited item
        if (this.items.containsKey(id)) {
            ProductDTO dto = this.items.get(id);
            int currentQuantity = dto.getQuantity();
            // kix thuat update value. truyen vao bien quantity
            quantity = quantity + currentQuantity;
            dto.setQuantity(quantity);
        } // items exited

        if (productListDTO == null) {
            dao.setProductList();
            productListDTO = dao.getProductList();
        }// productList has existed
        
        for (ProductDTO dto : productListDTO) {
            if (dto.getItemName().equals(id)) {
            dto.setQuantity(quantity); // set default quantity
                this.items.put(id, dto);
                result = true;
            } // finish add DTO to Items
        }// travese DTO list

        return result;
    }

    public boolean removeItemFromCart(String id, int quantity) {
        boolean result = false;
        ProductDTO productDTO = this.items.get(id);
        //1. check data validation
        if (id == null) {
            return result;
        }

        if (id.trim().isEmpty()) {
            return result;
        }

        if (quantity <= 0) {
            return result;
        }

        //2. check existed items
        if (this.items == null) {
            return result;
        }

        //3. check exited items
        if (!this.items.containsKey(id)) {
            return result;
        }

        //4. decrease quantity
        int currentQuantity = this.items.get(id).getQuantity();

        if (currentQuantity >= quantity) {
            quantity = currentQuantity - quantity;
        }

        if (quantity == 0) {
            this.items.remove(id);
            // Kieu Collection phai check khac null va size > 0;
            // Khi remove thif kiem tra so phan tu no = 0 thi set lai = null
            // sap toi check lai thi chi can check khac null la chac chan se ton` tai trong gio.
            if (this.items.isEmpty()) {
                this.items = null;
            }
        } else {
            productDTO.setQuantity(quantity);
            this.items.put(id, productDTO);
        }
        result = true;

        return result;
    }
}
