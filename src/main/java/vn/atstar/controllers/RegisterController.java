package vn.atstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.atstar.models.UserModel;
import vn.atstar.services.Impl.UserService;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String images = req.getParameter("images"); // có thể để trống
        String phone = req.getParameter("phone");
        int roleId = Integer.parseInt(req.getParameter("roleId")); // Nhận giá trị từ dropdown
        Date createDay = new Date();

        // Mã hóa mật khẩu trước khi lưu vào CSDL
        String hashedPassword = hashPassword(password);

        // Tạo đối tượng UserModel với thông tin đã nhập
        UserModel user = new UserModel(0, username, email, hashedPassword, fullname, images, phone, roleId, createDay);
        
        // Gọi phương thức register để lưu người dùng vào CSDL
        boolean isSuccess = userService.register(user);

        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/login"); // Chuyển hướng đến trang đăng nhập nếu thành công
        } else {
            req.setAttribute("error", "Đăng ký không thành công!"); // Hiển thị thông báo lỗi
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Hoặc xử lý lỗi theo cách khác
        }
    }
}
