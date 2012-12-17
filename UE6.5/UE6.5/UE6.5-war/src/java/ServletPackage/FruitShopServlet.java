/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletPackage;

import FruitBasketBean.FruitBasketBeanRemote;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tobias
 */
public class FruitShopServlet extends HttpServlet {

    @EJB
    FruitBasketBeanRemote bean;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();

        String query = request.getQueryString();
        Enumeration<String> a = request.getHeaderNames();

        String button = request.getParameter("button");

        if (userPath.equalsIgnoreCase("/FruitShopServlet")) {
            if (request.getParameter("button").equalsIgnoreCase("Send")) {
                bean.changeFruitShopByName(request.getParameter("selected_shop"));

                String availableFruits = bean.getAvailableFruits();
                request.setAttribute("availableFruits", availableFruits);

                String basketContent = bean.getBasketContent();
                request.setAttribute("basketContent", basketContent);

                int totalCost = bean.getTotalCost();
                request.setAttribute("totalCost", totalCost);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
                dispatcher.forward(request, response);
            } else if (request.getParameter("button").equalsIgnoreCase("Refresh shoplist")) {
                List<String> shoplist = bean.getFruitShops();
                int i = 0;
                for (String shop : shoplist) {
                    response.addHeader(i + "shop", shop);
                    request.setAttribute(i + "shop", shop);
                    i++;
                }
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
            if (request.getParameter("button").equals("Add fruit")) {
                bean.addFruitByName(request.getParameter("selected_fruit"));
                
                String availableFruits = bean.getAvailableFruits();
                request.setAttribute("availableFruits", availableFruits);

                String basketContent = bean.getBasketContent();
                request.setAttribute("basketContent", basketContent);

                int totalCost = bean.getTotalCost();
                request.setAttribute("totalCost", totalCost);
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
                dispatcher.forward(request, response);
                //response.sendRedirect("/index.jsp");
            } else if (request.getParameter("button").equals("Remove fruit")) {
                bean.removeFruitByName(request.getParameter("selected_fruit"));
                
                String availableFruits = bean.getAvailableFruits();
                request.setAttribute("availableFruits", availableFruits);

                String basketContent = bean.getBasketContent();
                request.setAttribute("basketContent", basketContent);

                int totalCost = bean.getTotalCost();
                request.setAttribute("totalCost", totalCost);
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
                dispatcher.forward(request, response);
            } else if (request.getParameter("button").equals("Buy all fruits")) {
                bean.buyAllFruits();
                
                String availableFruits = bean.getAvailableFruits();
                request.setAttribute("availableFruits", availableFruits);

                String basketContent = bean.getBasketContent();
                request.setAttribute("basketContent", basketContent);

                int totalCost = bean.getTotalCost();
                request.setAttribute("totalCost", totalCost);
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
                dispatcher.forward(request, response);
            } else if (request.getParameter("button").equals("Return to shopselection")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
