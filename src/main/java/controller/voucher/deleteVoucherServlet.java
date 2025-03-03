package controller.voucher;

import DAO.VoucherDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "deleteVoucherServlet", urlPatterns = {"/voucherManagement/deleteVoucher"})
public class deleteVoucherServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        System.out.println("🗑 Attempting to delete voucher with ID: " + idParam);

        if (idParam != null) {
            try {
                int voucherId = Integer.parseInt(idParam);
                VoucherDAO voucherDAO = new VoucherDAO();

                boolean deleted = voucherDAO.deleteVoucher(voucherId);

                if (deleted) {
                    System.out.println("✅ Voucher deleted successfully: " + voucherId);
                    response.setStatus(HttpServletResponse.SC_OK);  // 200 OK
                } else {
                    System.out.println("⚠ No voucher found with ID: " + voucherId);
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 404 Not Found
                }
            } catch (NumberFormatException e) {
                System.err.println("❌ Invalid voucher ID: " + idParam);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // 400 Bad Request
            }
        } else {
            System.err.println("⚠ No ID received for deletion.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // 400 Bad Request
        }
    }
}
