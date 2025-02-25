package controller.MuaHang;

import DAO.PaymentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Payment;

@WebServlet("/paymentList")
public class PaymentListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/Quannuoc/Login.jsp").forward(request, response);
        } else {
            String view = request.getParameter("TaiKhoan");
            if (view == null || view.equals("list")) {
                PaymentDAO paymentDAO = new PaymentDAO();

                int page = 1;
                int pageSize = 10; // Number of items per page
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                int totalPayments = paymentDAO.getTotalPaymentCount();
                int totalPages = (int) Math.ceil((double) totalPayments / pageSize);

                ArrayList<Payment> payments = paymentDAO.getPaymentsByPage(page, pageSize);

                request.setAttribute("paymentList", payments);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", totalPages);

                request.getRequestDispatcher("/Quanly/DonHang/paymentList.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
