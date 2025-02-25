package controller.inventory;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.InventoryDAO;

/**
 * @author Vo Truong Qui - CE181170
 */
@WebServlet(name = "AddStocktoInventoryServlet", urlPatterns = {"/Inventory/addStocktoInventory"})
public class AddStocktoInventoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from the request
        String dishName = request.getParameter("dish_name");  // Tên món ăn
        String supplierName = request.getParameter("supplier_name");  // Tên nhà cung cấp
        String contactInfo = request.getParameter("contact_info");  // Thông tin liên hệ
        int quantity = Integer.parseInt(request.getParameter("quantity"));  // Số lượng
        double purchasePrice = Double.parseDouble(request.getParameter("purchase_price"));  // Giá nhập
        double sellingPrice = Double.parseDouble(request.getParameter("selling_price"));  // Giá bán

        // Create an instance of InventoryDAO to call insertInventory method
        InventoryDAO inventoryDAO = new InventoryDAO();

        // Call insertInventory method to add the stock to inventory
        boolean isInserted = inventoryDAO.insertInventory(dishName, supplierName, contactInfo, quantity, purchasePrice, sellingPrice);

        // Set appropriate message based on insert result
        if (isInserted) {
            request.setAttribute("message", "Thêm hàng vào kho thành công!");
        } else {
            request.setAttribute("message", "Món ăn không tồn tại hoặc thêm hàng thất bại.");
        }

        // Forward the request to the JSP page for displaying the message
        request.getRequestDispatcher("/Inventory/checkInventoryQuantity").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet để thêm hàng vào kho";
    }
}
