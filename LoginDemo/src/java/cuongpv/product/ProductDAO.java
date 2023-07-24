/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.product;

import cuongpv.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author datnt
 */
public class ProductDAO implements Serializable{
    // Tương tác với DTO
    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList() 
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if(con != null) {
                String sql = "Select sku, name, price, quantity, status "
                        + "from dbo.Product";
                // fix lai cho nay ohai la createStatement
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()) {
                    String itemId = rs.getString("sku");
                    String itemName = rs.getString("name");
                    Integer itemPrice = rs.getInt("price");
                    Integer itemQuantity = rs.getInt("quantity");
                    Boolean isAvailable = rs.getBoolean("status");
                    ProductDTO dto = 
                    new ProductDTO(itemId, itemName, itemPrice, itemQuantity, isAvailable);
                    
                    if(this.productList == null) {
                        this.productList = new ArrayList<>();
                    }// DTO OBject has exsited
                    
                    this.productList.add(dto);
                } // result set has not EOF
            }   // conecction is available
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if(con != null) {
                con.close();
            }
        }
    }
    
}
