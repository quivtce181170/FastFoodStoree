/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TaiKhoan;

import DAO.TaiKhoanDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.TaiKhoan;

/**
 *
 * @author Phan Hồng Tài - CE181490
 */
@WebServlet(name = "QuanliTaiKhoan", urlPatterns = {"/QuanliTaiKhoan"})
public class QuanliTaiKhoan extends HttpServlet {

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
            String view = request.getParameter("TaiKhoan");
            if (view == null || view.equals("list")) {

                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                int pageSize = 5; // số tài khoản trên mỗi trang
                int page = 1; // trang mặc định là 1

// Lấy tham số trang từ request, nếu không có thì mặc định là trang 1
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

// Tính toán tổng số trang
                int totalTaiKhoan = tkDAO.getTotalTaiKhoan();
                int totalPages = (int) Math.ceil((double) totalTaiKhoan / pageSize);

// Lấy danh sách tài khoản cho trang hiện tại
                ArrayList<TaiKhoan> taiKhoans = tkDAO.getTaiKhoanByPage(page, pageSize);

                request.setAttribute("tk", taiKhoans);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", totalPages);

                request.getRequestDispatcher("/TaiKhoan/list.jsp").forward(request, response);

            } else if (view == null || view.equals("create")) {
                request.getRequestDispatcher("/TaiKhoan/create.jsp").forward(request, response);
            } else if (view == null || view.equals("edit")) {
                String idParam = request.getParameter("id");
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();

                // Kiểm tra nếu id là rỗng
                if (idParam == null || idParam.isEmpty()) {
                    request.setAttribute("errorMessage", "ID cannot be empty.");
                    request.getRequestDispatcher("/TaiKhoan/edit.jsp").forward(request, response);
                    return;
                }

                int id;
                try {
                    id = Integer.parseInt(idParam);
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid ID format.");
                    request.getRequestDispatcher("/TaiKhoan/edit.jsp").forward(request, response);
                    return;
                }

                TaiKhoan currentAccount = tkDAO.getOnlyById(id);
                if (currentAccount == null) {
                    request.setAttribute("errorMessage", "Account not found.");
                } else {
                    request.setAttribute("taiKhoan", currentAccount);
                }

                request.getRequestDispatcher("/TaiKhoan/edit.jsp").forward(request, response);
            } else if (view == null || view.equals("delete")) {
                String idParam = request.getParameter("id");
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();

                // Kiểm tra nếu id là rỗng
                if (idParam == null || idParam.isEmpty()) {
                    request.setAttribute("errorMessage", "ID cannot be empty.");
                    request.getRequestDispatcher("/TaiKhoan/delete.jsp").forward(request, response);
                    return;
                }

                int id;
                try {
                    id = Integer.parseInt(idParam);
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid ID format.");
                    request.getRequestDispatcher("/TaiKhoan/delete.jsp").forward(request, response);
                    return;
                }

                TaiKhoan currentAccount = tkDAO.getOnlyById(id);
                if (currentAccount == null) {
                    request.setAttribute("errorMessage", "Account not found.");
                } else {
                    request.setAttribute("taiKhoan", currentAccount);
                }
                request.getRequestDispatcher("/TaiKhoan/delete.jsp").forward(request, response);
            }

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
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean isNhanVien = request.getParameter("isNhanVien") != null;
            boolean isAdmin = request.getParameter("isAdmin") != null;

            TaiKhoanDAO tkDAO = new TaiKhoanDAO();

            // Kiểm tra nếu username đã tồn tại
            if (tkDAO.existsByUsername(username)) {
                request.setAttribute("errorMessage", "Username already exists. Please choose a different one.");
                request.getRequestDispatcher("/TaiKhoan/create.jsp").forward(request, response);
            } else {
                TaiKhoan newTaiKhoan = new TaiKhoan();
                newTaiKhoan.setUsername(username);
                newTaiKhoan.setPassword(password);
                newTaiKhoan.setIsNhanVien(isNhanVien);
                newTaiKhoan.setIsAdmin(isAdmin);

                int result = tkDAO.create(newTaiKhoan);

                if (result > 0) {
                    response.sendRedirect(request.getContextPath() + "/QuanliTaiKhoan");
                } else {
                    request.setAttribute("errorMessage", "Failed to create the account.");
                    request.getRequestDispatcher("/TaiKhoan/create.jsp").forward(request, response);
                }
            }
        } else if (action.equals("edit")) {
            TaiKhoanDAO tkDAO = new TaiKhoanDAO();

            // Lấy dữ liệu từ form
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean isNhanVien = request.getParameter("isNhanVien") != null;
            boolean isAdmin = request.getParameter("isAdmin") != null;

            // Lấy ID từ form
            String idParam = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid ID format.");
                request.getRequestDispatcher("/TaiKhoan/edit.jsp").forward(request, response);
                return;
            }

            // Kiểm tra nếu username đã tồn tại (ngoại trừ tài khoản hiện tại)
            TaiKhoan currentAccount = tkDAO.getOnlyById(id);
            if (tkDAO.existsByUsername(username) && !username.equals(currentAccount.getUsername())) {
                request.setAttribute("errorMessage", "Username already exists. Please choose a different one.");
                request.getRequestDispatcher("/TaiKhoan/edit.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng TaiKhoan mới với thông tin đã cập nhật
            TaiKhoan updatedTaiKhoan = new TaiKhoan(id, username, password, isNhanVien, isAdmin);
            if (tkDAO.update(updatedTaiKhoan) > 0) {
                response.sendRedirect(request.getContextPath() + "/QuanliTaiKhoan");
            } else {
                request.setAttribute("errorMessage", "Failed to update the account.");
                request.getRequestDispatcher("/TaiKhoan/edit.jsp").forward(request, response);
            }
        } else if (action.equals("delete")) {
            String idParam = request.getParameter("id");
            TaiKhoanDAO tkDAO = new TaiKhoanDAO();

            // Kiểm tra nếu id là rỗng
            if (idParam == null || idParam.isEmpty()) {
                request.setAttribute("errorMessage", "ID cannot be empty.");
                request.getRequestDispatcher("/TaiKhoan/delete.jsp").forward(request, response);
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid ID format.");
                request.getRequestDispatcher("/TaiKhoan/delete.jsp").forward(request, response);
                return;
            }

            // Thực hiện xóa tài khoản
            if (tkDAO.delete(id) == 1) {
                response.sendRedirect(request.getContextPath() + "/QuanliTaiKhoan");
            } else {
                request.setAttribute("errorMessage", "Failed to delete the account.");
                request.setAttribute("taiKhoan", tkDAO.getOnlyById(id)); // Lấy tài khoản để hiển thị trong delete.jsp
                request.getRequestDispatcher("/TaiKhoan/delete.jsp").forward(request, response);
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
