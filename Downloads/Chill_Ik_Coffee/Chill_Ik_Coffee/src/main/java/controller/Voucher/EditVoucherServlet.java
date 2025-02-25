package controller.Voucher;


import DAO.VoucherDAO;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Voucher;

@WebServlet(name = "EditVoucherServlet", urlPatterns = {"/Quanly/voucher/EditVoucher"})
public class EditVoucherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID voucher từ request
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher"); // Nếu không tìm thấy ID hợp lệ, chuyển về danh sách
            return;
        }

        // Lấy thông tin voucher từ CSDL
        VoucherDAO dao = new VoucherDAO();
        Voucher voucher = dao.getOnlyById(id);
        if (voucher == null) {
            response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher");
            return;
        }

        request.setAttribute("voucher", voucher);
        request.getRequestDispatcher("/Quanly/voucher/edit-voucher.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid voucher ID.");
            doGet(request, response);
            return;
        }

        // Nhận các tham số từ biểu mẫu
        String name = request.getParameter("name");
        double discount;
        try {
            discount = Double.parseDouble(request.getParameter("discount"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid discount value.");
            doGet(request, response);
            return;
        }

        // Nhận số lượng voucher từ biểu mẫu
        int quantity;
        try {
            quantity = Integer.parseInt(request.getParameter("quantity")); // Lấy số lượng voucher
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid quantity value.");
            doGet(request, response);
            return;
        }

        String expiryDateString = request.getParameter("expiry_date");
        Date expiryDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(expiryDateString);
            expiryDate = new Date(utilDate.getTime());
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Invalid expiry date.");
            doGet(request, response);
            return;
        }

        // Xác thực các giá trị đầu vào
        if (isValidInput(name, discount, expiryDate, quantity)) {
            VoucherDAO vDAO = new VoucherDAO();
            Voucher voucher = new Voucher(id, name, discount, expiryDate, quantity); // Sử dụng số lượng voucher từ biểu mẫu

            // Cập nhật voucher trong CSDL
            if (vDAO.update(voucher) == 1) {
                response.sendRedirect(request.getContextPath() + "/Quanly/voucher/ListVoucher");
                return;
            }
        }

        // Nếu xác thực thất bại, đặt thông báo lỗi và chuyển tiếp lại đến biểu mẫu
        request.setAttribute("errorMessage", "Please enter valid input values.");
        doGet(request, response);
    }

    private boolean isValidInput(String name, double discount, Date expiryDate, int quantity) {
        return name != null && !name.trim().isEmpty() && discount >= 0 && discount <= 100 && expiryDate != null && quantity > 0; // Kiểm tra số lượng voucher
    }
}
