/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author datnt
 */
public class LogFactory {
    
    public static Logger getSystemLogger(String file) {
        Logger logger = Logger.getLogger("Server"+file+"log");
        FileHandler fileHandler = null;
        
        try {
            fileHandler = new FileHandler("D:/NETBEAN8.2/LogFolder/Login_Demo/system"+file+".log");
        } catch (IOException ex) {
            Logger.getLogger(LogFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(LogFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        return logger;
    }
    
    public static Logger getUserLogger(String file) {
        Logger logger = Logger.getLogger("User"+file+"Log");
        FileHandler fileHandler = null;
        
        try {
            fileHandler = new FileHandler("D:/NETBEAN8.2/LogFolder/Login_Demo/user"+file+".log");
        } catch (IOException ex) {
            Logger.getLogger(LogFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(LogFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        return logger;
    }
        
}
