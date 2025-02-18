package controller.TaiKhoan;

import DAO.DonDKDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.donDK;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet for handling employee registration.
 */
@WebServlet(name = "DonDK", urlPatterns = {"/DonDK"})
public class DonDK extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DonDK.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Quannuoc/donDK.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data with basic validation
        String tenNV = request.getParameter("tenNV");
        String sdtStr = request.getParameter("Sdt");
        String diachi = request.getParameter("diachi");
        String ngaysinh = request.getParameter("ngaysinh");
        String quequan = request.getParameter("quequan");

        if (tenNV == null || tenNV.isEmpty() || sdtStr == null || sdtStr.isEmpty()
                || diachi == null || diachi.isEmpty() || ngaysinh == null || ngaysinh.isEmpty()
                || quequan == null || quequan.isEmpty()) {
            request.setAttribute("message", "Vui lòng điền đầy đủ thông tin.");
            request.getRequestDispatcher("/Quannuoc/donDK.jsp").forward(request, response);
            return;
        }

        // Tạo đối tượng donDK
        donDK dondk = new donDK();
        dondk.setTenNV(tenNV);
        dondk.setSdt(sdtStr); // Số điện thoại
        dondk.setDiachi(diachi);
        dondk.setNgaysinh(ngaysinh);
        dondk.setQuequan(quequan);
        dondk.setTrangThai("Chưa xử lý"); // Gán trạng thái mặc định
        dondk.setNgayNhanDon(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // Ngày nhận đơn là ngày hiện tại

        // Use DonDKDAO to save data
        DonDKDAO donDKDAO = new DonDKDAO();
        int result = donDKDAO.create(dondk);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/trangchu");
        } else {
            request.setAttribute("mess", "Không thể tạo tài khoản. Vui lòng thử lại.");
            request.getRequestDispatcher("/Quannuoc/donDK.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for handling employee registration";
    }
}
