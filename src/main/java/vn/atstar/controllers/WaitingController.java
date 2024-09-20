package vn.atstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.atstar.models.UserModel;

@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Không tạo session mới nếu không có

        // Kiểm tra session và account trong session
        if (session != null && session.getAttribute("account") != null) {
            UserModel user = (UserModel) session.getAttribute("account");
            req.setAttribute("username", user.getUsername());

            // Điều hướng dựa trên roleId
            String redirectPath = getRedirectPath(user.getRoleId());
            resp.sendRedirect(req.getContextPath() + redirectPath);
        } else {
            // Nếu không có session hoặc account, điều hướng về trang login
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    private String getRedirectPath(int roleId) {
        switch (roleId) {
            case 1: // Admin
                return "/admin/home";
            case 2: // Manager
                return "/manager/home";
            case 3: // User
                return "/user/home";
            case 4: // Seller
                return "/seller/home";
            case 5: // Shipper
                return "/shiper/home";
            default: // Nếu roleId không xác định
                return "/login";
        }
    }
}
