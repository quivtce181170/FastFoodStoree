package controller.Voucher;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.VoucherDAO; // Đảm bảo rằng bạn có DAO tương ứng
import java.sql.Date;
import java.text.SimpleDateFormat;
import model.Voucher; // Đảm bảo rằng bạn có Model tương ứng

@WebServlet(name="CreateVoucherServlet", urlPatterns={"/Quanly/voucher/CreateVoucher"})
public class CreateVoucherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Quanly/voucher/create-voucher.jsp").forward(request, response); // Chuyển tiếp đến JSP
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nhận các tham số từ biểu mẫu
        String name = request.getParameter("name");
        int discount = Integer.parseInt(request.getParameter("discount"));
        String expiryDateString = request.getParameter("expiry_date");
        int quantity = Integer.parseInt(request.getParameter("quantity")); // Nhận số lượng

        // Chuyển đổi chuỗi ngày sang java.sql.Date
        Date expiryDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(expiryDateString);
            expiryDate = new Date(utilDate.getTime());
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }

        // Xác thực các giá trị đầu vào
        if (isValidInput(name, discount, expiryDate, quantity)) {
            VoucherDAO vDAO = new VoucherDAO();
            // Khởi tạo Voucher với id = 0 (id sẽ tự động được tạo trong CSDL)
            if (vDAO.create(new Voucher(0, name, discount, expiryDate, quantity)) == 1) {
                response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher"); // Chuyển hướng đến danh sách voucher
                return;
            }
        }

        // Nếu xác thực thất bại, đặt thông báo lỗi và chuyển tiếp lại đến biểu mẫu
        request.setAttribute("errorMessage", "Please enter valid input values.");
        doGet(request, response); // Chuyển tiếp lại đến doGet để hiển thị biểu mẫu với lỗi
    }

    private boolean isValidInput(String name, int discount, Date expiryDate, int quantity) {
        return name != null && !name.trim().isEmpty() && discount >= 0 && discount <= 100 
                && expiryDate != null && quantity > 0 && quantity < 100; // Thêm kiểm tra cho số lượng
    }
}
