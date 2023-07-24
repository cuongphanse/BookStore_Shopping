/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongpv.controller;

import cuongpv.registration.RegistrationDAO;
import cuongpv.registration.RegistrationDTO;
import cuongpv.utils.LogFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.html";
    private final String RESULT_PAGE = "search.jsp";

    static final Logger LOGGER = Logger.getLogger(SearchLastnameServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        String searchValue = request.getParameter("txtSearchValue");
        String url = RESULT_PAGE;

        try {
            //Paramaters khong bang null khi khong ton tai nen check Rong thoi
            if (!searchValue.trim().isEmpty()) {
                LOGGER.info("User search successs!");
                //4. Call DAO
                //4.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //4.2 Call method
                dao.searchLastname(searchValue);
                // Can truye ndu~ lieu di thi se ton tai 4.3
                //4.3 Lay duoc kq cua viec search thong qua LIST DTO (luu tru kq cho Scope) [[if any]]
                List<RegistrationDTO> result = dao.getAccountList();
                // Xd noi luu tru va cach luu tru.
                request.setAttribute("SEARCH_RESULT", result);
                request.setAttribute("txtLastSearchValue", searchValue);
                url = RESULT_PAGE;
            } // end search Value has valid value;
            
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        
        } catch (SQLException ex) {
            LOGGER.warn(" Connect SQL DB fail at " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (NamingException ex) {
            LOGGER.warn(" Fail Config JNDI at " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } finally {
            //Do su dung [reqScope] nen khi response.Sendirect() se tra ve Res Msg 
            // thi se khong the luu nua.
//            out.close();
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
