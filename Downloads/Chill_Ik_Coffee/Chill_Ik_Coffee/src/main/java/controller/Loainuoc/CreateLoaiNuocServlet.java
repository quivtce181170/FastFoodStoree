package controller.Loainuoc;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.LoaiNuocDAO; // Đảm bảo rằng bạn có DAO tương ứng
import model.LoaiNuoc; // Đảm bảo rằng bạn có Model tương ứng

@WebServlet(name = "CreateLoaiNuocServlet", urlPatterns = {"/Quanly/Loainuoc/CreateLoaiNuoc"})

public class CreateLoaiNuocServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Quanly/Loainuoc/create.jsp").forward(request, response);

    }

  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String name = request.getParameter("name");

    if (isValidInput(name)) {
        LoaiNuocDAO lDAO = new LoaiNuocDAO();
        if (lDAO.create(new LoaiNuoc(0, name)) == 1) {
           
            response.sendRedirect(request.getContextPath() + "/Quanly/Loainuoc/ListLoaiNuoc");
            return;
        }
    }

    request.setAttribute("errorMessage", "Please enter valid input values.");
    doGet(request, response);
}


    private boolean isValidInput(String name) {
        return name != null && !name.trim().isEmpty(); // Kiểm tra tên không trống
    }
}
