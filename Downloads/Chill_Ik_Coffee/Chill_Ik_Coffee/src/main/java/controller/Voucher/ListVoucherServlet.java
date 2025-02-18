package controller.Voucher;

import DAO.VoucherDAO;
import model.Voucher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ListVoucherServlet", urlPatterns = {"/Quanly/voucher/ListVoucher"})
public class ListVoucherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VoucherDAO voucherDAO = new VoucherDAO();

        // Gọi phương thức xóa các voucher đã hết hạn
        voucherDAO.deleteExpiredVouchers();
        // Xóa các voucher có số lượng = 0
        voucherDAO.deleteVouchersWithZeroQuantity();

        int pageSize = 5; // Số lượng voucher trên mỗi trang
        int page = 1; // Mặc định là trang đầu tiên

        // Lấy số trang từ request, nếu không có thì mặc định là trang 1
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int totalVouchers = voucherDAO.getTotalVouchers();
        int totalPages = (int) Math.ceil((double) totalVouchers / pageSize);

        ArrayList<Voucher> vouchers = voucherDAO.getVouchersByPage(page, pageSize);

        request.setAttribute("vouchers", vouchers);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/Quanly/voucher/list-voucher.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ID voucher từ yêu cầu
        String voucherIdStr = request.getParameter("voucherId");

        if (voucherIdStr != null && !voucherIdStr.isEmpty()) {
            int voucherId = Integer.parseInt(voucherIdStr);
            VoucherDAO voucherDAO = new VoucherDAO();

            // Gọi phương thức giảm số lượng voucher
            int result = voucherDAO.reduceVoucherQuantity(voucherId);

            if (result > 0) {
                request.setAttribute("message", "Voucher đã được giảm số lượng thành công!");
            } else {
                request.setAttribute("message", "Không thể giảm số lượng voucher. Kiểm tra lại ID voucher hoặc số lượng còn lại.");
            }
        }

        // Sau khi giảm số lượng, tải lại danh sách voucher và chuyển tiếp đến trang hiển thị
        doGet(request, response);
    }
}
