package controller.voucher;

import DAO.VoucherDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Date;
import model.Voucher;

@WebServlet(name = "EditVoucherServlet", urlPatterns = {"/voucherManagement/editVoucher"})
public class EditVoucherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int voucherId = Integer.parseInt(request.getParameter("id"));
            VoucherDAO voucherDAO = new VoucherDAO();
            Voucher voucher = voucherDAO.getVoucherById(voucherId);
            
            if (voucher != null) {
                request.setAttribute("voucher", voucher);
                request.getRequestDispatcher("/voucherManagement/editVoucher.jsp").forward(request, response);
            } else {
                response.sendRedirect("/voucherManagement/viewVoucher?error=Voucher Not Found");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("/voucherManagement/viewVoucher?error=Invalid Voucher ID");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int voucherId = Integer.parseInt(request.getParameter("voucherId"));
            String name = request.getParameter("name");
            BigDecimal discountPercentage = new BigDecimal(request.getParameter("discount"));
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            Date validFrom = Date.valueOf(request.getParameter("validFrom"));
            Date validUntil = Date.valueOf(request.getParameter("validUntil"));
            String status = request.getParameter("status");
            
            Voucher updatedVoucher = new Voucher(voucherId, name, discountPercentage, soLuong, validFrom, validUntil, status, null, null);
            VoucherDAO voucherDAO = new VoucherDAO();
            boolean isUpdated = voucherDAO.updateVoucher(updatedVoucher);

            if (isUpdated) {
                response.sendRedirect("/voucherManagement/viewVoucher?success=Voucher Updated Successfully");
            } else {
                response.sendRedirect("/voucherManagement/editVoucher?id=" + voucherId + "&error=Failed to update voucher");
            }
        } catch (Exception e) {
            response.sendRedirect("/voucherManagement/editVoucher?id=" + request.getParameter("voucherId") + "&error=Invalid Input");
        }
    }
}
