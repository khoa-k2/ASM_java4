package poly.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import poly.com.dao.UserDAO;
import poly.com.dao.UserDAOImpl;
import poly.com.model.User;

import java.io.IOException;

import Filter.AuthFilter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAOImpl dao = new UserDAOImpl();
       @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	   HttpSession session = req.getSession(false);
    	    Object user = (session != null) ? session.getAttribute("user") : null;

    	    // Nếu đã login rồi
    	    if (user != null) {
    	        req.setAttribute("warning", "Bạn đang đăng nhập. Bạn có muốn đăng xuất không?");
    	    }
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
            String securityUri = (String)session.getAttribute(AuthFilter.SECURITY_URI);
            // Kiểm tra phân quyền (nếu bạn có thuộc tính role)
            req.setAttribute("success", "Đăng nhập thành công");
            if (securityUri != null) {
                session.removeAttribute(AuthFilter.SECURITY_URI);
                
                resp.sendRedirect(securityUri);  // quay về đúng trang vừa bấm
            } else {
                resp.sendRedirect(req.getContextPath() + "/home"); // hoặc trang chủ bạn muốn
            }
        } else {
            // login thất bại
            req.setAttribute("error", "Tên đăng nhập/email hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
 
   
}
