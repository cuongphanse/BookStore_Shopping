/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.registration;

import cuongpv.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author kenan
 */
public class RegistrationDAO implements Serializable {

    static final Logger LOGGER = Logger.getLogger(RegistrationDAO.class);
//    public boolean checkLogin(String userName, String password)
//            throws SQLException, NamingException /*ClassNotFoundException*/ {

    public RegistrationDTO checkLogin(String userName, String password)
            throws SQLException, NamingException /*ClassNotFoundException*/ {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        // tu dong 29 - 43: connect DB va Query
        //1. Connect DB
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //check connection truoc khi su dung SQL code
                //2. Write SQL command
                String sql = "Select lastName, isAdmin "
                        + "from Registration "
                        + "Where userName = ? And password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                // Nếu có params thì phải thiết lập tham số vào trong stm.
                stm.setString(1, userName);
                stm.setString(2, password);
                //4. Excecute Statement Object to get result.
                rs = stm.executeQuery();
                //5. Process result.    
                //tu 45 - result : set value;
                if (rs.next()) {
                    String fullName = rs.getString("lastName");
                    boolean role = rs.getBoolean("isAdmin");

                    result = new RegistrationDTO(userName, null, fullName, role);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

//    tuong tac voi DTO
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<RegistrationDTO> accountList) {
        this.accountList = accountList;
    }

    public void searchLastname(String keyword) throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        // tu dong 29 - 43: connect DB va Query
        //1. Connect DB
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //check connection truoc khi su dung SQL code
                //2. Write SQL command
                String sql = "Select userName, password, lastName, isAdmin "
                        + "from Registration "
                        + "Where lastName LIKE ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                // Nếu có params thì phải thiết lập tham số vào trong stm.
                //4. Excecute Statement Object to get result.
                stm.setString(1, "%" + keyword + "%");
                rs = stm.executeQuery();
                //5. Process result.    
                while (rs.next()) {
                    // Lay ten Column.
                    String username = rs.getString("userName");
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastName");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, lastName, isAdmin);
                    // Muon add vao list phai check null truoc roi moi add
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }
                    //end account list has Not existed
                    this.accountList.add(dto);
                } //end result set has not reached EOF

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String pk)
            throws SQLException, NamingException /*ClassNotFoundException*/ {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        // tu dong 29 - 43: connect DB va Query
        //1. Connect DB
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //check connection truoc khi su dung SQL code
                //2. Write SQL command
                String sql = "Delete From Registration "
                        + "Where userName = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                // Nếu có params thì phải thiết lập tham số vào trong stm.
                stm.setString(1, pk);
                //4. Excecute Statement Object to get result.
                int effectRows = stm.executeUpdate();
                //5. Process result.    
                if (effectRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }

    public boolean updateAccount(String pk, String password, boolean isAdmin)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        // tu dong 29 - 43: connect DB va Query
        //1. Connect DB
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "Where userName = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, pk);

                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    result = true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }

    // Nene truyen vao dto(javabean) vao ham
    public boolean createAccount(RegistrationDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        // tu dong 29 - 43: connect DB va Query
        //1. Connect DB
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //check connection truoc khi su dung SQL code
                String sql = "Insert Into Registration ("
                        + "userName, password, lastName, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUserName());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getLastName());
                stm.setBoolean(4, dto.isRole());

                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
