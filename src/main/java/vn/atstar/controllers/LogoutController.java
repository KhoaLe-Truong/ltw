package vn.atstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.atstar.utils.constant;

import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kết thúc session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Xóa cookie nhớ tên đăng nhập
        Cookie cookie = new Cookie(constant.COOKIE_REMEMBER, null);
        cookie.setMaxAge(0); // Xóa cookie ngay lập tức
        cookie.setPath("/"); // Đảm bảo cookie được xóa cho toàn bộ ứng dụng
        resp.addCookie(cookie);

        // Chuyển hướng về trang đăng nhập
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
