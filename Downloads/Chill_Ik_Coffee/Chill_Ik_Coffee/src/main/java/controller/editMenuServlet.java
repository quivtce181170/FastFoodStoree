/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.DrinkDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import model.Drink;

/**
 *
 * @author Nguyen Ngoc Phat - CE180321
 */
@WebServlet(name = "editMenuServlet", urlPatterns = {"/EditDrink"})
public class editMenuServlet extends HttpServlet {

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
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/Quannuoc/Login.jsp").forward(request, response);
        } else {
            String idParam = request.getParameter("id");
            DrinkDAO dDAO = new DrinkDAO();

            if (idParam == null || idParam.isEmpty()) {
                request.setAttribute("errorMessage", "There is no drink with that id");
                request.getRequestDispatcher("/Quanly/monnuoc/edit_monnuoc_quanly.jsp").forward(request, response);
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "There is no drink with that id");
                request.getRequestDispatcher("/Quanly/monnuoc/edit_monnuoc_quanly.jsp").forward(request, response);
                return;
            }

            Drink d = dDAO.getOnlyById(id);
            if (d == null) {
                request.setAttribute("errorMessage", "There is no drink with that id");
            } else {
                request.setAttribute("drink", d);
            }
            request.getRequestDispatcher("/Quanly/monnuoc/edit_monnuoc_quanly.jsp").forward(request, response);
        }
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
        DrinkDAO dDAO = new DrinkDAO();

        String name = request.getParameter("name");
        int gia = Integer.parseInt(request.getParameter("gia"));
        int soluong = Integer.parseInt(request.getParameter("so_luong"));
        int loainuocid = Integer.parseInt(request.getParameter("loai_nuoc_id"));
        boolean trangthai = request.getParameter("trang_thai") != null;
        String imageUrl = request.getParameter("imageUrl");

        int id = Integer.parseInt(request.getParameter("id"));

        Drink updatedDrink = new Drink(id, name, gia, soluong, loainuocid, trangthai, imageUrl);
        if (dDAO.update(updatedDrink) == 1) {
            response.sendRedirect(request.getContextPath() + "/Menu");
        } else {
            out.println("<p>There is no drink with that id</p>");
        }
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
