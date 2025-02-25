package controller.workschedules;

import DAO.StaffScheduleDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet xử lý cập nhật lịch làm việc cho nhân viên
 *
 * @author Vo Truong Qui - CE181170
 */
@WebServlet(name = "UpdateStaffScheduleServlet", urlPatterns = {"/updateStaffSchedule"})
public class UpdateStaffScheduleServlet extends HttpServlet {

 @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.getRequestDispatcher("/updateStaffSchedule.jsp").forward(request, response);
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy shiftId từ request
            String idParam = request.getParameter("shiftId");
            if (idParam == null || idParam.isEmpty()) {
                response.sendRedirect("staffSchedule?update=fail&error=invalid_id");
                return;
            }
            int shiftId = Integer.parseInt(idParam);

            // Lấy các tham số khác từ request
            String employeeName = request.getParameter("employeeName");
            String shiftDate = request.getParameter("shiftDate");
            String shiftTime = request.getParameter("shiftTime");
            String status = request.getParameter("status");
            String notes = request.getParameter("notes");
            String managerName = request.getParameter("managerName");
            String replacementEmployeeName = request.getParameter("replacementEmployeeName");

            // Gọi DAO để cập nhật lịch làm việc
            StaffScheduleDAO dao = new StaffScheduleDAO();
            boolean success = dao.updateSchedule(shiftId, employeeName, managerName,
                    replacementEmployeeName, shiftDate,
                    shiftTime, status, notes);

            // Điều hướng sau khi cập nhật
            if (success) {
                // Redirect về servlet hiển thị danh sách lịch làm việc
                response.sendRedirect("staffSchedule");  // Chuyển hướng đến servlet hiển thị danh sách lịch làm việc
            } else {
                response.sendRedirect("staffSchedule?update=fail&error=db_update_failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("staffSchedule?update=fail&error=invalid_id_format");
        } catch (Exception e) {
            response.sendRedirect("staffSchedule?update=fail&error=unknown");
        }
    }
}
