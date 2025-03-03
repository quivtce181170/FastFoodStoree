package controller.voucher;

import DAO.VoucherDAO;
import model.Voucher;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddVoucherServlet", urlPatterns = {"/voucherManagement/addVoucher"})
public class addVoucherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/voucherManagement/addVoucher.jsp").forward(request, response); // Chuyển tiếp đến JSP
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            BigDecimal discount = new BigDecimal(request.getParameter("discount"));
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date validFrom = sdf.parse(request.getParameter("validFrom"));
            Date validUntil = sdf.parse(request.getParameter("validUntil"));
            String status = request.getParameter("status");

            Date now = new Date();
            Voucher newVoucher = new Voucher(0, name, discount, soLuong, validFrom, validUntil, status, now, now);
            VoucherDAO voucherDAO = new VoucherDAO();

            // In ra log trước khi thêm vào database
            System.out.println("Adding new voucher: " + newVoucher.getName());

            voucherDAO.addVoucher(newVoucher);

            // In ra log sau khi thêm vào database
            System.out.println("Voucher added successfully!");

            response.sendRedirect(request.getContextPath() + "/voucherManagement/viewVoucher");
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("viewVoucher.jsp?error=Invalid Input");
        }
    }
}
