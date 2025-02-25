package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAO.DrinkDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Drink;

/**
 *
 * @author Nguyen Ngoc Phat - CE180321
 */
@WebServlet(urlPatterns = {"/CreateDrink"})
public class createMenuServlet extends HttpServlet {

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
        request.getRequestDispatcher("/Quanly/monnuoc/create_monnuoc_quanly.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/Quannuoc/Login.jsp").forward(request, response);
        } else {
            String name = request.getParameter("name");
            int gia = Integer.parseInt(request.getParameter("gia"));
            int soluong = Integer.parseInt(request.getParameter("so_luong"));
            int loainuocid = Integer.parseInt(request.getParameter("loai_nuoc_id"));
            boolean trangthai = request.getParameter("trang_thai") != null;
            String imageUrl = request.getParameter("imageUrl");

            if (name != null && !name.trim().isEmpty()
                    && gia >= 0 && gia <= 10000000
                    && soluong >= 0 && soluong <= 100000000
                    && loainuocid >= 0 && loainuocid <= 100000000
                    && imageUrl != null && !imageUrl.trim().isEmpty()) {

                DrinkDAO dDAO = new DrinkDAO();

                Drink newDrink = new Drink(name, gia, soluong, loainuocid, trangthai, imageUrl);
                if (dDAO.create(newDrink) == 1) {
                    response.sendRedirect(request.getContextPath() + "/Menu");
                } else {
                    request.setAttribute("errorMessage", "Failed to create drink.");
                    request.getRequestDispatcher("create_monnuoc_quanly.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Please enter valid input values.");
                request.getRequestDispatcher("create_monnuoc_quanly.jsp").forward(request, response);
            }
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
