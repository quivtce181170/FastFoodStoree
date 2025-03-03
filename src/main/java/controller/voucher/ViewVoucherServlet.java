/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.voucher;

import DAO.VoucherDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Voucher;

/**
 * Servlet implementation class ViewVoucherServlet
 */
@WebServlet(name = "ViewVoucherServlet", urlPatterns = {"/voucherManagement/viewVoucher"})
public class ViewVoucherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VoucherDAO voucherDAO = new VoucherDAO();
        List<Voucher> vouchers = voucherDAO.getAllVouchers();

        System.out.println("Number of vouchers retrieved: " + (vouchers != null ? vouchers.size() : "null"));

        request.setAttribute("vouchers", vouchers);  // Gửi danh sách vouchers đến JSP
        request.getRequestDispatcher("/voucherManagement/viewVoucher.jsp").forward(request, response);
    }
}
