/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author datnt
 */
public class MyContextServletListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("APLICATION IS DEPLOYYYYYYYYYYYYYY");
        ServletContext context = sce.getServletContext();
        String siteMapsPath = context.getInitParameter("STIE_MAPS_PATH");
        //properties
        Properties siteMaps = new Properties();
        InputStream is = null;

        try {
            is = context.getResourceAsStream(siteMapsPath);
            siteMaps.load(is);
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            Logger.getLogger(MyContextServletListner.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    
                }
            }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce
    ) {
        System.out.println("APLICATION IS DESTROYYYYYYY");
    }
}
