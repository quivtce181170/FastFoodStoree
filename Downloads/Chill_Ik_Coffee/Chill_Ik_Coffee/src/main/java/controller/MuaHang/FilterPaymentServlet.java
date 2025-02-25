/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.MuaHang;

import DAO.PaymentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Payment;

/**
 *
 * @author Do Van Luan - CE180457
 */
@WebServlet("/filterPayment")
public class FilterPaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status"); 
        PaymentDAO paymentDAO = new PaymentDAO();

    int page = 1;
    int pageSize = 10; // Set default page size
    if (request.getParameter("page") != null) {
        page = Integer.parseInt(request.getParameter("page"));
    }

    List<Payment> paymentList;
    int totalPayments;
    if (status != null && !status.isEmpty() && !status.equals("All")) {
        // Filter by status with pagination
        paymentList = paymentDAO.getPaymentsByStatusAndPage(status, page, pageSize);
        totalPayments = paymentDAO.getTotalPaymentCountByStatus(status);
    } else {
        // Get all payments with pagination if no status or "All" is selected
        paymentList = paymentDAO.getPaymentsByPage(page, pageSize);
        totalPayments = paymentDAO.getTotalPaymentCount();
    }

    int totalPages = (int) Math.ceil((double) totalPayments / pageSize);

    request.setAttribute("paymentList", paymentList);
    request.setAttribute("currentStatus", status);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);

    request.getRequestDispatcher("/Quanly/DonHang/paymentList.jsp").forward(request, response);
    }
}
