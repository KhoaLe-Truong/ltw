package vn.atstar.controllers;

import java.io.IOException;
import vn.atstar.utils.constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.atstar.models.UserModel;
import vn.atstar.services.IUserService;
import vn.atstar.services.Impl.UserService;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    IUserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra cookie để tự động điền tên người dùng
        String username = getCookieValue(req, constant.COOKIE_REMEMBER);
        if (username != null) {
            req.setAttribute("uname", username);
        }
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy tham số từ view
        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        String remember = req.getParameter("remember");

        // Kiểm tra tham số
        boolean isRememberMe = "on".equals(remember);
        String alertMsg = "";

        // Kiểm tra rỗng
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Xử lý đăng nhập
        UserModel user = service.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            if (isRememberMe) {
                saveRememberMe(resp, username);
            }
            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletResponse resp, String username) {
        Cookie cookie = new Cookie(constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 24 * 60 * 60); // Cookie tồn tại 30 ngày
        cookie.setPath("/"); // Đặt đường dẫn cho cookie
        resp.addCookie(cookie);
    }

    private String getCookieValue(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
