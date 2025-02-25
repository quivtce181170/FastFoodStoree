/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TaiKhoan;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TaiKhoan;

/**
 *
 * @author Phan Hồng Tài - CE181490
 */
@WebServlet(name = "loginControl", urlPatterns = {"/login"})
public class loginControl extends HttpServlet {

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
        request.getRequestDispatcher("/Quannuoc/Login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();

        /* 1. // Kiểm tra thông tin đăng nhập
                if (userDAO.login(username, password)) {
                  //response.sendRedirect("/Quannuoc/Giaodien.jsp");
                  request.getRequestDispatcher("/Quannuoc/Giaodien.jsp").forward(request, response);
                } else {
                    request.setAttribute("mess", "Invalid username or password.");
                    request.getRequestDispatcher("/Quannuoc/Login.jsp").forward(request, response);
                }
         */
        HttpSession session = request.getSession();

        /* 2. // Kiểm tra thông tin đăng nhập
                if (userDAO.login(username, password)) {
                   
                    // Nếu đăng nhập thành công, thiết lập phiên làm việc và chuyển hướng
                    session.setAttribute("user", username); // Lưu thông tin người dùng vào session
                    response.sendRedirect(request.getContextPath()+"/trangchu"); // Chuyển hướng tới movie.jsp
                } else {
session.setAttribute("mess", "Invalid username or password.");
                  request.getRequestDispatcher("/Quannuoc/Login.jsp").forward(request, response);
                }*/
        TaiKhoan userAccount = userDAO.AccTaiKhoanlogin(username, password);

        if (userAccount != null) {
            session.setAttribute("user", userAccount); // Lưu toàn bộ đối tượng TaiKhoan vào session
            session.setAttribute("id", userAccount.getId()); // Store user ID in session
            if (userAccount.isIsAdmin()) {
                
                response.sendRedirect(request.getContextPath() + "/trangchu"); // Chuyển hướng tới trang chủ
                
            } else {
                response.sendRedirect(request.getContextPath() + "/Menu"); // Chuyển hướng tới trang chủ

            }
        } else {
            session.setAttribute("mess", "Invalid username or password.");
            request.getRequestDispatcher("/Quannuoc/Login.jsp").forward(request, response);
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
