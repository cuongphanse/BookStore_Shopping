/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.controller;

import cuongpv.cart.CartObj;
import cuongpv.product.ProductDTO;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author datnt
 */
@WebServlet(name = "RemoveItemsFromCartServlet", urlPatterns = {"/RemoveItemsFromCartServlet"})
public class RemoveItemsFromCartServlet extends HttpServlet {

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
        
        try {
            //1. Customer goes to cart plase
            HttpSession session = request.getSession(false);
            // Khi delete thì phải check false bởi bì có thể không  tương tác thì 
            // session timeout nhưng view vẫn còn.
            // checked session != null for if cust doesnt add any item in session(cart)
            if(session != null) {
                //2. Customer takes his/her cart
                CartObj cart = (CartObj)session.getAttribute("CART");
                if(cart != null) {
                    //3. Customer gets all items
                    Map<String, ProductDTO> items = cart.getItems();
                    if(items != null) {
                        //4. Customer chooses items
                        String[] selectedItems = request.getParameterValues("checkItem");
                        if(selectedItems != null ) {
                            //5. Customer removes items form cart
                            for( String item : selectedItems) {
                                cart.removeItemFromCart(item, 1);
                            } // end removed
                            session.setAttribute("CART", cart);
                        }// end items had choicced
                    } // end items have exsited
                }// end cart has existed
            } // seesion has existed
            
        } finally { 
            // call View Cart features again using urlRewriting
            String urlRewriting = "DispatchServlet"
                    + "?btnAction=ViewCart";
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
