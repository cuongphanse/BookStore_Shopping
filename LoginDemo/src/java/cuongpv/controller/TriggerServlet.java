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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author datnt
 */
@WebServlet(name = "TriggerServlet", urlPatterns = {"/TriggerServlet"})
public class TriggerServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            //1. get all cookies
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //2. get [Last Cookies]
                Cookie lastCookies = cookies[cookies.length - 1];
                //3. getUsername & password in cookies
                String userName = lastCookies.getName();
                String password = lastCookies.getValue();

                //4. Call model
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO result = dao.checkLogin(userName, password);
                // checklogin thanh cong
                if (result != null) {
                    url = SEARCH_PAGE;
//                    response.sendRedirect(url);
                }

            } // end cookies had existed
        } catch (SQLException ex) {
//            response.sendError(response.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (NamingException ex) {
//            response.sendError(response.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        } finally {
            // Dung cai gi cung duoc . Muon che duong truyen hay khong thoi. 
            // Do cookie o file client.
           response.sendRedirect(url);

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
