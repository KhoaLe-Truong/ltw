package vn.atstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.atstar.services.Impl.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = {"/forgot-password"})
public class ForgetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String newPassword = req.getParameter("newPassword");

        // Kiểm tra rỗng
        if (username == null || username.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            req.setAttribute("error", "Tên đăng nhập và mật khẩu mới không được để trống!");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        boolean isUpdated = userService.updatePassword(username, newPassword);
        if (isUpdated) {
            req.setAttribute("message", "Mật khẩu đã được cập nhật thành công!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Không tìm thấy tài khoản hoặc cập nhật không thành công!");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        }
    }
}
