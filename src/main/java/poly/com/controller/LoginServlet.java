package poly.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import poly.com.dao.UserDAO;
import poly.com.dao.UserDAOImpl;
import poly.com.model.User;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAOImpl dao = new UserDAOImpl();
       @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("Id");
        String password = req.getParameter("Password");

        User user = dao.checkLogin(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // Kiểm tra phân quyền (nếu bạn có thuộc tính role)
            if (user.isAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/manager");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }

        } else {
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu sai!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
 
   
}
