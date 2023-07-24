/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.utils;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;



/**
 *
 * @author kenan
 */
public class DBHelper implements Serializable {
    public static Connection makeConnection()  
    throws SQLException, NamingException{
//        throws ClassNotFoundException, SQLException{
        
        //1. get current Context
        Context context = new InitialContext();
        //2. get Tomcat Context
//        Cấu hình dựa vào JNBI thông qua biến lookup.
        Context tomcat = (Context) context.lookup("java:comp/env");
        //3. Look up DS
        DataSource ds = (DataSource) tomcat.lookup("LordDS");
        //4. oppen connection
        Connection con = ds.getConnection();    
        
        return con;
        
        
//      // 1. Load Driver
//            // Cách 1. load driver ở thời điểm runtime.
//      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            // cách 2: 
////                DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
//      // 2. Create url Connection string --> DB Address
//      String url = "jdbc:sqlserver://localhost:1433;databaseName=Authentication;instanceName=DATNT";
//   
//      // 3. Open Connection
//      Connection con = DriverManager.getConnection(url, "sa", "datnt271102");
//      
//      return con;
      
    };
    
    public static int toInt_Password(String password) 
        throws NumberFormatException {
            int passwordNumberType = Integer.parseInt(password);
            return passwordNumberType;
    }
    
}
  
	
