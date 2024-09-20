package vn.atstar.controllers.admin;

import java.io.IOException;
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

@WebServlet(urlPatterns = {"/shiper/home"})
public class ShiperHomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Không tạo session mới nếu không có
        
        if (session != null && session.getAttribute("account") != null) {
            UserModel user = (UserModel) session.getAttribute("account");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/views/shiperhome.jsp").forward(req, resp);
        } else {
            // Kiểm tra cookie nếu session không tồn tại
            Cookie[] cookies = req.getCookies();
            String username = null;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        username = cookie.getValue();
                    }
                }
            }

            // Nếu tìm thấy cookie username, thực hiện đăng nhập tự động
            if (username != null) {
                UserModel user = userService.FindByUserName(username);
                if (user != null) {
                    session = req.getSession(true);
                    session.setAttribute("account", user);
                    req.setAttribute("username", user.getUsername());
                    req.getRequestDispatcher("/views/shiperhome.jsp").forward(req, resp);
                    return;
                }
            }
            
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
