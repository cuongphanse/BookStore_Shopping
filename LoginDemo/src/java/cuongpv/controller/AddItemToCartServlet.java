/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.controller;

import cuongpv.cart.CartObj;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author datnt
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "shopping.jsp";
    static final Logger LOGGER = Logger.getLogger(AddItemToCartServlet.class);

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
        String urlRewriting = null;
        try {
            //1. Customer goes to cart place;   
            int quantity = 0;
            String numberItem = request.getParameter("numberItem");
            if (!numberItem.trim().isEmpty()) {
                quantity = Integer.parseInt(numberItem);
                LOGGER.info("Quantity is validate");
            }
            HttpSession session = request.getSession();
            // Khonog can check session vi getSession(true);
            //2. Customer takes his/her shopping cart.
            CartObj cart = (CartObj) session.getAttribute("CART");
            //check cart exsited
            if (cart == null) {
                cart = new CartObj();
            }
            //3. Customer drops items to cart
            String item = request.getParameter("ddlBook");
            // Lay them so luong mua.

            cart.addItemToCart(item, quantity);
            // cart o client vi minh da tao moi roi
            // Lay seesion xong phai set lai.
            session.setAttribute("CART", cart);
            
            urlRewriting = "DispatchServlet"
                    + "?btnAction=viewBookList";
            //4. Customer continous goes shopoing (load lai trang shopping.
        } catch (NamingException ex) {
//         
        } catch (SQLException ex) {
//            
        } catch (NumberFormatException ex) {
            LOGGER.warn("Quantity is invalid");
        } finally {
            // che duong truyen thi xai forward.
//            response.sendRedirect(SHOPPING_PAGE);
            RequestDispatcher rq = request.getRequestDispatcher(urlRewriting);
            rq.forward(request, response);
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
