/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.controller;

import cuongpv.registration.RegistrationDAO;
import cuongpv.registration.RegistrationDTO;
import java.io.IOException;
import java.sql.SQLException;
//import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author kenan
 */
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_PAGE_JSP = "search.jsp";

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Controller send cho view va render va return cho controller va dang duoc luu tai bien url.
        String url = INVALID_PAGE;

        String userName = request.getParameter("userName");
        String password = request.getParameter("userPassword");

        try {
            //3. Call DAOmodel 
            //3.1 New ObjectModel
            RegistrationDAO dao = new RegistrationDAO();
//                Registration_IntDAO dao_int = new Registration_IntDAO();

            //3.2 Call method from that object
//                boolean result = dao.checkLogin(userName, password);
            // We need to take DTO user because we just store DTO thay vì nhiều attribute.
            RegistrationDTO result = dao.checkLogin(userName, password);

            //4. send View (su dung hang so)
            if (result != null) {
                LOGGER.info("Login Success");
                // Controller send cho view va render va return cho controller va dang duoc luu tai bien url.
                url = SEARCH_PAGE_JSP;
                //create session
                //Login susscess must store userdate. 
                // Base on data user we can check Authentication.
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", result);
                session.setMaxInactiveInterval(60 * 3);
                
//                store cookie  loginAccount
                Cookie cookie = new Cookie(userName, password);
                cookie.setMaxAge(60 * 3);
                response.addCookie(cookie);
                LOGGER.info("Redirect to Search Page");

            } else {
                LOGGER.info("Login Fail");
            }//end user clicked Login button
            response.sendRedirect(url);
        } catch (SQLException ex) {
            LOGGER.warn(" Connect SQL DB fail at " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (NamingException ex) {
            LOGGER.warn(" Fail Config JNDI at " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } finally {
            //5. Set value cho ResObj
            // Phải xài response.ssenRedirect. Vì muốn có được cookie phải trả về.
//            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
