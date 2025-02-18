package controller.Loainuoc;

import DAO.LoaiNuocDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoaiNuoc;

@WebServlet(name = "DeleteLoaiNuocServlet", urlPatterns = {"/Quanly/Loainuoc/DeleteLoaiNuoc"})
public class DeleteLoaiNuocServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("errorMessage", "Không tìm thấy loại nước với ID này.");
            request.getRequestDispatcher("/Quanly/Loainuoc/delete.jsp").forward(request, response);
            return;
        }
        
        int id = Integer.parseInt(idParam);
        LoaiNuocDAO loaiNuocDAO = new LoaiNuocDAO();
        LoaiNuoc loaiNuoc = loaiNuocDAO.getOneById(id);
        
        if (loaiNuoc == null) {
            request.setAttribute("errorMessage", "Không tìm thấy loại nước với ID này.");
            request.getRequestDispatcher("/Quanly/Loainuoc/delete.jsp").forward(request, response);
            return;
        }
        
        request.setAttribute("loaiNuoc", loaiNuoc);
        request.getRequestDispatcher("/Quanly/Loainuoc/delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LoaiNuocDAO loaiNuocDAO = new LoaiNuocDAO();
        loaiNuocDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/Quanly/Loainuoc/ListLoaiNuoc");
    }

    @Override
    public String getServletInfo() {
        return "Servlet xóa loại nước";
    }
}
