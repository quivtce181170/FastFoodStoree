/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TaiKhoan;

import DAO.TaiKhoanDAO;
import DAO.UserDAO;
import java.io.IOException;
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
@WebServlet(name = "registerServlet", urlPatterns = {"/register"})
public class registerServlet extends HttpServlet {

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
        request.getRequestDispatcher("/Quannuoc/DangkyTK.jsp").forward(request, response);
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
        String confirmPassword = request.getParameter("confirmPassword");
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        HttpSession session = request.getSession();

        // Kiểm tra nếu username đã tồn tại
        if (tkDAO.existsByUsername(username)) {
            request.setAttribute("mess", "Username đã tồn tại. Vui lòng chọn một tên khác.");
            request.getRequestDispatcher("/Quannuoc/DangkyTK.jsp").forward(request, response);
        } else {
            // Kiểm tra xác nhận mật khẩu
            if (confirmPassword.equals(password)) {
                TaiKhoan newTaiKhoan = new TaiKhoan();
                newTaiKhoan.setUsername(username);
                newTaiKhoan.setPassword(password);
                newTaiKhoan.setIsNhanVien(false);
                newTaiKhoan.setIsAdmin(false);
                UserDAO userDAO = new UserDAO();
                // Tạo tài khoản mới
                int result = tkDAO.create(newTaiKhoan);
                TaiKhoan userAccount = userDAO.AccTaiKhoanlogin(username, password);

                if (result > 0) {

                    session.setAttribute("user", userAccount);
                    response.sendRedirect(request.getContextPath() + "/trangchu");
                } else {
                    request.setAttribute("mess", "Không thể tạo tài khoản. Vui lòng thử lại.");
                    request.getRequestDispatcher("/Quannuoc/DangkyTK.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mess", "Mật khẩu xác nhận không khớp. Vui lòng nhập lại.");
                request.getRequestDispatcher("/Quannuoc/DangkyTK.jsp").forward(request, response);
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
        return "Servlet đăng ký tài khoản người dùng";
    }
}
