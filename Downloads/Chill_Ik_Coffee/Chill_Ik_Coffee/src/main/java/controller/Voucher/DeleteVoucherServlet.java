/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Voucher;

import DAO.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Voucher;

@WebServlet(name = "DeleteVoucherServlet", urlPatterns = {"/Quanly/voucher/DeleteVoucher"})
public class DeleteVoucherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy ID từ tham số yêu cầu
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int voucherId = Integer.parseInt(idParam);
                VoucherDAO voucherDAO = new VoucherDAO();

                // Kiểm tra ID hợp lệ và lấy thông tin phim
                Voucher selectedVoucher = voucherDAO.getOnlyById(voucherId);
                if (selectedVoucher != null) {
                    // Nếu hợp lệ, đưa thông tin phim vào request và chuyển đến trang xác nhận xóa
                    request.setAttribute("selectedVoucher", selectedVoucher);

                    request.getRequestDispatcher("/Quanly/voucher/delete-voucher.jsp").forward(request, response);
                } else {
                    // ID không hợp lệ, chuyển hướng về danh sách
                    response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher");
                }
            } catch (NumberFormatException e) {
                // ID không hợp lệ, chuyển hướng về danh sách
                response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher");
            }
        } else {
            // Không có ID, chuyển hướng về danh sách
            response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ID từ tham số yêu cầu
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int voucherId = Integer.parseInt(idParam);
               VoucherDAO voucherDAO = new VoucherDAO();

                // Thực hiện xóa
                int result = voucherDAO.delete(voucherId);

                if (result > 0) {
                    // Xóa thành công, chuyển hướng về danh sách
                    response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher");
                } else {
                    // Xóa thất bại, chuyển hướng về danh sách
                    response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher");
                }
            } catch (NumberFormatException e) {
                // ID không hợp lệ, chuyển hướng về danh sách
                response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher");
            }
        } else {
            // Không có ID, chuyển hướng về danh sách
            response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher");
        }
    }
}
