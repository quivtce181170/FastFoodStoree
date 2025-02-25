package controller.MuaHang;

import DAO.DoanhThuDAO;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DoanhThu;

@WebServlet("/Quanly/DoanhThu")
public class DoanhThuServlet extends HttpServlet {

    private DoanhThuDAO doanhThuDAO;

    @Override
    public void init() {
        doanhThuDAO = new DoanhThuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gọi các phương thức tổng doanh thu
        ArrayList<DoanhThu> tongDoanhThuTheoNgay = doanhThuDAO.getTongDoanhThuTheoNgay();
        ArrayList<DoanhThu> tongDoanhThuTheoTuan = doanhThuDAO.getTongDoanhThuTheoTuan();
        ArrayList<DoanhThu> tongDoanhThuTheoThang = doanhThuDAO.getTongDoanhThuTheoThang();
        ArrayList<DoanhThu> tongDoanhThuTheoNam = doanhThuDAO.getTongDoanhThuTheoNam(); // Lấy tổng doanh thu theo năm

        // Truyền dữ liệu tổng doanh thu
        request.setAttribute("tongDoanhThuTheoNgay", tongDoanhThuTheoNgay);
        request.setAttribute("tongDoanhThuTheoTuan", tongDoanhThuTheoTuan);
        request.setAttribute("tongDoanhThuTheoThang", tongDoanhThuTheoThang);
        request.setAttribute("tongDoanhThuTheoNam", tongDoanhThuTheoNam); // Truyền dữ liệu tổng doanh thu theo năm

        // Chuyển hướng tới doanhthu.jsp
        request.getRequestDispatcher("doanhthu.jsp").forward(request, response);
    }
}
