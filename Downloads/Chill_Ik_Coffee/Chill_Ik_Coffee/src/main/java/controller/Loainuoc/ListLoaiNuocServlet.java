/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Loainuoc;

import DAO.LoaiNuocDAO;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoaiNuoc;

/**
 * Servlet to handle requests for listing types of drinks.
 * 
 * @author Vo Truong Qui - CE181170
 */
@WebServlet(name = "ListLoaiNuocServlet", urlPatterns = {"/Quanly/Loainuoc/ListLoaiNuoc"})
public class ListLoaiNuocServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoaiNuocDAO loaiNuocDAO = new LoaiNuocDAO();
        ArrayList<LoaiNuoc> list = loaiNuocDAO.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/Quanly/Loainuoc/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoaiNuocDAO loaiNuocDAO = new LoaiNuocDAO();
        ArrayList<LoaiNuoc> list = loaiNuocDAO.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/Quanly/Loainuoc/list.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for listing types of drinks";
    }
}
