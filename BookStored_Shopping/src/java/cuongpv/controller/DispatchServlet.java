/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author datnt
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = loginPage;
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String TRIGGER_APP_CONTROLLER = "TriggerServlet";
    
    private final String SHOW_BOOK_LIST_CONTROLLER = "ShowBookListServlet"; 
    private final String ADD_BOOK_TOCART_CONTROLLER = "AddItemToCartServlet";
    private final String REMOVE_ITEMS_FORM_CART_CONTROLLER = "RemoveItemsFromCartServlet";
    private final String CHECKOUT_CONTROLLER = "CheckOutServlet";
    
    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";

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
        PrintWriter out = response.getWriter();
        
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        
        String url = LOGIN_PAGE;
        //which btn did user click?
        String btn = request.getParameter("btnAction");
        
        try {
            if(btn == null) {
                url = TRIGGER_APP_CONTROLLER;
            } else if (btn.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (btn.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (btn.equals("Search")) {
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (btn.equals("Delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (btn.equals("Update")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (btn.equals("viewBookList")) {
                url = SHOW_BOOK_LIST_CONTROLLER;
            }else if (btn.equals("AddToCart")) {
                url = ADD_BOOK_TOCART_CONTROLLER;
            }else if (btn.equals("ViewCart")) {
                url = "viewCart.jsp";
            } else if (btn.equals("RemoveSelectedItems")) {
                url = REMOVE_ITEMS_FORM_CART_CONTROLLER;
            } else if (btn.equals("CheckOut")) {
                url = CHECKOUT_CONTROLLER;
            } else if  (btn.equals("CreateNewAccount")) {
                url = CREATE_ACCOUNT_CONTROLLER;
            }
        
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request,response);
            
            out.close();
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
