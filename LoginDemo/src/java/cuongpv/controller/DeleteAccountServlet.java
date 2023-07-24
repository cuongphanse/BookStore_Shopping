/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.controller;

import cuongpv.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author datnt
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

    private final String ERRORS_PAGE = "errors.html";

    static final Logger LOGGER = Logger.getLogger(DeleteAccountServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String urlRewriting = ERRORS_PAGE;
        String userName = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");

        try {
            //Call Model
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.deleteAccount(userName);

            if (result) {
                LOGGER.info("Delete Succcess " + userName);
                //call the Search feature by using url rewriting.
                urlRewriting = "DispatchServlet"
                        + "?btnAction=Search"
                        + "&txtSearchValue=" + searchValue;
//                response.sendRedirect(urlRewriting);
                LOGGER.info("Redirect to Search Page");
            } else {
                LOGGER.info("Delete fail");
            }

        } catch (SQLException ex) {
            LOGGER.warn(" Connect SQL DB fail at " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.warn(" Fail Config JNDI at " + ex.getMessage());
        } finally {
            // neu su dung forward thi khi  trung param se duoc day vao 1 mang. do v 
            // se khong the phat hien duoc chuc nang nao
            // do delete roi nen k can luu du lieu nen se su dung send direct.
            response.sendRedirect(urlRewriting);
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
