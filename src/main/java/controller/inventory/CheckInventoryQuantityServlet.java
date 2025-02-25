package controller.inventory;

import DAO.InventoryDAO;
import model.Inventory;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="CheckInventoryQuantityServlet", urlPatterns={"/Inventory/checkInventoryQuantity"})
public class CheckInventoryQuantityServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Get the sorted inventory list from the DAO
        InventoryDAO inventoryDAO = new InventoryDAO();
        List<Inventory> inventoryList = inventoryDAO.getInventorySortedByQuantity();
        
        // Set the inventory list as a request attribute to be accessed in the JSP page
        request.setAttribute("inventoryList", inventoryList);
        
        // Forward the request to the JSP page
        request.getRequestDispatcher("/Inventory/checkInventoryQuantity.jsp").forward(request, response);
    }

    // Override doGet and doPost methods
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
}
