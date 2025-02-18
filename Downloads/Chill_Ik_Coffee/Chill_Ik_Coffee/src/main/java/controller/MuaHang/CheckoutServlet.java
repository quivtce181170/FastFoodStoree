package controller.MuaHang;

import DAO.DrinkDAO;
import DAO.VoucherDAO;
import DAO.PaymentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartItem;
import model.Voucher;
import model.Payment;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy session và thông tin giỏ hàng
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        DrinkDAO drinkDAO = new DrinkDAO();
        VoucherDAO voucherDAO = new VoucherDAO();

        // Kiểm tra giỏ hàng
        if (cart == null || cart.isEmpty()) {
            request.setAttribute("message", "Giỏ hàng trống, vui lòng chọn nước.");
            request.getRequestDispatcher("/Menu").forward(request, response);
            return;
        }

        // Lấy thông tin các mặt hàng trong giỏ hàng
        List<CartItem> cartItems = drinkDAO.getCartItems(cart);
        request.setAttribute("cartItems", cartItems);

        // Lấy danh sách voucher có sẵn
        List<Voucher> vouchers = voucherDAO.getAll();
        request.setAttribute("vouchers", vouchers);

        // Tính tổng giá ban đầu
        double totalPrice = drinkDAO.calculateTotalPrice(cart);
        request.setAttribute("totalPrice", totalPrice);

        // Chuyển hướng đến trang checkout
        request.getRequestDispatcher("/Nguoidung/checkout.jsp").forward(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy session và thông tin giỏ hàng
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        DrinkDAO drinkDAO = new DrinkDAO();
        VoucherDAO voucherDAO = new VoucherDAO();
        PaymentDAO paymentDAO = new PaymentDAO();

        // Kiểm tra giỏ hàng
        if (cart == null || cart.isEmpty()) {
            request.setAttribute("message", "Giỏ hàng trống, vui lòng chọn nước.");
            request.getRequestDispatcher("/Menu").forward(request, response);
            return;
        }

      // Kiểm tra xem userId có trong session hay không
        Integer userId = (Integer) session.getAttribute("id");
        if (userId == null) {
            // Nếu userId chưa được lưu trong session, chuyển hướng người dùng đến trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

    
  
        // Lấy thông tin từ form
        String action = request.getParameter("action");

        if ("confirmPayment".equals(action)) {
            // Lấy voucher được chọn
            int voucherId = Integer.parseInt(request.getParameter("voucherId"));
            Voucher voucher = voucherDAO.getOnlyById(voucherId);

            // Lấy địa chỉ và số điện thoại
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

            // Tính tổng giá
            double totalPrice = drinkDAO.calculateTotalPrice(cart);
            double discount = voucher != null ? totalPrice * (voucher.getGiamGia() / 100.0) : 0;
            double totalAfterDiscount = totalPrice - discount;

            // Tạo đối tượng Payment
            Payment payment = new Payment();
            payment.setUserId(userId);
            payment.setVoucherId(voucherId);
            payment.setTotalAmount(totalPrice);
            payment.setDiscountAmount(discount);
            payment.setFinalAmount(totalAfterDiscount);
            payment.setAddress(address);
            payment.setPhone(phone);
            payment.setStatus("Pending");

            // Lưu thanh toán vào cơ sở dữ liệu
            paymentDAO.addPayment(payment);

            // Xóa giỏ hàng sau khi thanh toán thành công
            session.removeAttribute("cart");

            if (voucherId != 0) {
                int result = voucherDAO.reduceVoucherQuantity(voucherId);
                if (result == 0) {
                    System.out.println("Không thể giảm số lượng voucher, có thể voucher đã hết số lượng.");
                }
            }
            
            // Chuyển hướng đến trang xác nhận thanh toán
            response.sendRedirect(request.getContextPath() + "/Nguoidung/checkoutConfirmation.jsp");
        } else {
            // Xử lý các hành động khác nếu cần
            response.sendRedirect(request.getContextPath() + "/checkout");
        }
    }
}
