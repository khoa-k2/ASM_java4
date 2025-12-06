package poly.com.controller;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.ShareDAO;
import poly.com.dao.VideoDAOImpl;
import poly.com.model.Share;
import poly.com.model.User;
import poly.com.model.Video;
@WebServlet("/video/share")
public class VideoShareServlet extends HttpServlet{


	  private ShareDAO shareDAO = new ShareDAO();
	    private VideoDAOImpl videoDAO = new VideoDAOImpl();

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        Object userObj = req.getSession().getAttribute("user");
	        if (userObj == null) {
	            // Chưa đăng nhập → chuyển đến login
	            resp.sendRedirect(req.getContextPath() + "/login.jsp");
	            return;
	        }

	        User user = (User) userObj;
	        String videoId = req.getParameter("id");
	        Video video = videoDAO.findById(videoId);

	        if (video != null) {
	            // Lưu vào bảng Share
	            Share share = Share.builder()
	                               .user(user)
	                               .video(video)
	                               .shareDate(new Date())
	                               .build();
	            shareDAO.save(share);

	            // Tăng lượt xem nếu muốn
	            videoDAO.increaseView(videoId);

	            resp.getWriter().write("{\"status\":\"success\",\"videoId\":\"" + videoId + "\"}");
	        } else {
	            resp.getWriter().write("{\"status\":\"error\",\"message\":\"Video không tồn tại\"}");
	        }
	    }
}
