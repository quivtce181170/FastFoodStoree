package controller.MuaHang;

import DAO.DrinkDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartItem;
import model.Drink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session and the cart
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>(); // Initialize cart if it doesn't exist
        }

        // Get product ID and quantity from the request
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Add the item to the cart
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);
        session.setAttribute("cart", cart); // Save the cart in session

        String loainuocidParam = request.getParameter("loai_nuoc_id");
        String pageParam = request.getParameter("page");

        String redirectUrl = "/Menu" ;
        response.sendRedirect(request.getContextPath() + redirectUrl);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/Quannuoc/Login.jsp").forward(request, response);
        } else {
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
            DrinkDAO drinkDAO = new DrinkDAO();
            List<CartItem> cartItems = new ArrayList<>();

            double totalPrice = 0.0;

            if (cart != null) {
                for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                    int productId = entry.getKey();
                    int quantity = entry.getValue();
                    Drink drink = drinkDAO.getOnlyById(productId);
                    if (drink != null) {
                        double price = drink.getGia();
                        totalPrice += price * quantity; // Calculate total price
                        cartItems.add(new CartItem(drink.getName(), quantity, price, price * quantity));
                    }
                }
            }

            // Handle voucher if provided
            String voucherCode = request.getParameter("voucherCode");
            double discount = 0.0; // Assume 0% discount by default
            if (voucherCode != null && !voucherCode.isEmpty()) {
                // You need to implement voucher validation and discount calculation logic here
                // For example, you could call a method to get the discount percentage based on the voucher code
                // discount = getDiscountFromVoucher(voucherCode);
            }

            // Apply discount if any
            double finalPrice = totalPrice * (1 - discount / 100);

            // Set attributes to be accessed in the JSP
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("finalPrice", finalPrice);
            request.setAttribute("voucherCode", voucherCode); // Send the voucher code back to the view

            request.getRequestDispatcher("/Nguoidung/cart.jsp").forward(request, response);
        }
    }
}
