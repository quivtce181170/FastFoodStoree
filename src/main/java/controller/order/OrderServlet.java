/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.order;

/**
 *
 * @author Vo Truong Qui - CE181170
**/

import DAO.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Order;

@WebServlet(name = "OrderServlet", urlPatterns = {"/createOrder"})
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("user_id"));
            BigDecimal totalAmount = new BigDecimal(request.getParameter("total_amount"));
            String paymentMethod = request.getParameter("payment_method");
            String deliveryAddress = request.getParameter("delivery_address");
            Date estimatedDeliveryDate = null;

            if (request.getParameter("estimated_delivery_date") != null && !request.getParameter("estimated_delivery_date").isEmpty()) {
                estimatedDeliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("estimated_delivery_date"));
            }

            Order order = new Order(userId, totalAmount, paymentMethod, deliveryAddress, estimatedDeliveryDate);
            OrderDAO orderDAO = new OrderDAO();

            if (orderDAO.createOrder(order)) {
                request.setAttribute("message", "Tạo đơn hàng thành công!");
            } else {
                request.setAttribute("error", "Không thể tạo đơn hàng!");
            }
            request.getRequestDispatcher("/createOrder.jsp").forward(request, response);

        } catch (NumberFormatException | ParseException e) {
            request.setAttribute("error", "Dữ liệu nhập vào không hợp lệ!");
            request.getRequestDispatcher("/createOrder.jsp").forward(request, response);
        }
    }
}

