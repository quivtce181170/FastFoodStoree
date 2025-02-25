package controller;

import DAO.DrinkDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Drink;

/**
 * @author Nguyen Ngoc Phat - CE180321
 */
@WebServlet(name = "menuServlet", urlPatterns = {"/Menu"})
public class menuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int pageSize = 12;
        String loainuocidParam = request.getParameter("loai_nuoc_id");

        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        DrinkDAO drinkDAO = new DrinkDAO();
        ArrayList<Drink> drinks;

        Optional<Integer> loainuocid = Optional.ofNullable(loainuocidParam)
                .map(Integer::parseInt);

        if (loainuocid.isPresent()) {
            drinks = drinkDAO.getOnlyByLoaiNuocId(loainuocid.get(), page, pageSize);
        } else {
            drinks = drinkDAO.getDrinksByPage(page, pageSize);
        }

        int totalDrinks = drinkDAO.getTotalDrinkCount();
        int totalPages = totalDrinks > 0 ? (int) Math.ceil((double) totalDrinks / pageSize) : 1;

        request.setAttribute("drinks", drinks);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        if (loainuocidParam != null && !loainuocid.isPresent()) {
            request.setAttribute("errorMessage", "Loại nước không hợp lệ. Hiển thị tất cả đồ uống.");
        }

        request.getRequestDispatcher("/Nguoidung/menu.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Menu Servlet to display drink list.";
    }
}
