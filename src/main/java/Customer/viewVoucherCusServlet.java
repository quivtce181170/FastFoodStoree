/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Customer;

import DAO.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Voucher;

/**
 *
 * @author Admin
 */
@WebServlet(name = "viewVoucherCusServlet", urlPatterns = {"/Customer/viewVoucherCusServlet"})
public class viewVoucherCusServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VoucherDAO voucherDAO = new VoucherDAO();
        List<Voucher> vouchers = voucherDAO.getAllVouchers();

        System.out.println("Number of vouchers retrieved: " + (vouchers != null ? vouchers.size() : "null"));

        request.setAttribute("vouchers", vouchers);  // Gửi danh sách vouchers đến JSP
        request.getRequestDispatcher("/viewVoucherCustomer.jsp").forward(request, response);

    }

}
