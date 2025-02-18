package controller.MuaHang;

import DAO.PaymentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Payment;

@WebServlet("/addPayment")
public class AddPaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward request to the payment form page (checkout.jsp)
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get information from the form
            int userId = Integer.parseInt(request.getParameter("userId"));
            int voucherId = Integer.parseInt(request.getParameter("voucherId"));
            double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
            double discountAmount = Double.parseDouble(request.getParameter("discountAmount"));
            double finalAmount = Double.parseDouble(request.getParameter("finalAmount"));

            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String status = "Success"; // Set default status

            // Create a Payment object
            Payment payment = new Payment();
            payment.setUserId(userId);
            payment.setVoucherId(voucherId);
            payment.setTotalAmount(totalAmount);
            payment.setDiscountAmount(discountAmount);
            payment.setFinalAmount(finalAmount);

            payment.setAddress(address);
            payment.setPhone(phone);
            payment.setStatus(status);

            // Add payment to the database
            PaymentDAO paymentDAO = new PaymentDAO();
            paymentDAO.addPayment(payment);

            // Redirect to confirmation page
            response.sendRedirect("confirmation.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp");
        }
    }
}
