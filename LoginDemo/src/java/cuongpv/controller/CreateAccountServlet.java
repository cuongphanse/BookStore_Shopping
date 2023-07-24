/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.controller;

import cuongpv.registration.RegistrationCreateError;
import cuongpv.registration.RegistrationDAO;
import cuongpv.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(CreateAccountServlet.class);
    private final String ERROR_PAAGE = "register.jsp";
    private final String LOGGIN_PAGE = "login.html";

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

        String url = ERROR_PAAGE;

        String userName = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        String passwordConfirm = request.getParameter("txtPasswordConfirm");
        String fullName = request.getParameter("txtFullName");
        // De bat loi can tao 1 bien co de check coi loi hay khong
        boolean foundError = false;
        // Tao doi tuong de bat loi
        RegistrationCreateError errors = new RegistrationCreateError();

        try {
            //1. check user'validation 
            if (userName.trim().length() < 6
                    || userName.trim().length() > 20) {
                foundError = true;
                errors.setFullNameLengthError("Username is required input form 6 to 20 characters");
            }

            if (password.trim().length() < 6
                    || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password is required input form 6 to 30 characters");
            } else if (!passwordConfirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatched("Confirm must matched password");
            }

            if (fullName.trim().length() < 2
                    || fullName.trim().length() > 50) {
                foundError = true;
                errors.setFullNameLengthError("Full name is required input form 2 to 50 characters");
            }

            //2. process
            //2.1 if errors occur, system displays errors and log errors.
            //2.2 otherwise, call Model - DAO
            if (foundError) {
                request.setAttribute("CREATE_ERROR", errors);

            } else { // no errors
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(userName, password, fullName, false);

                boolean result = dao.createAccount(dto);
                if (result) {
                    url = LOGGIN_PAGE;
                } // create action is successfull
            }
        } catch (NamingException ex) {
            LOGGER.warn("CreateAccountServlet _ Naming" + ex.getMessage());
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            LOGGER.warn("CreateAccountServlet _ SQL" + msg);
            if(msg.contains("duplicate")) {
                errors.setUserNameIsExisted(userName + " is Existed");
                // set errors
                request.setAttribute("CREATE_ERROR", errors);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
