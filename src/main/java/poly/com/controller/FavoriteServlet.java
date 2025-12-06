package poly.com.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.VideoDAOImpl;
import poly.com.model.Video;
@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet{

	 private VideoDAOImpl videoDAO = new VideoDAOImpl();

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        // Lấy page hiện tại (phân trang)
	        String pageParam = req.getParameter("page");
	        int page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);

	        int limit = 6;         // mỗi trang hiển thị 6 video
	        int offset = (page - 1) * limit;

	        // Lấy ra danh sách video top views
	        List<Video> list = videoDAO.getTopVideos(limit, offset);

	        req.setAttribute("favorites", list);
	        req.setAttribute("currentPage", page);

	        // Nếu bạn muốn tính tổng trang
	        int totalVideos = videoDAO.countAll();   // bạn có thể tự viết hàm này
	        int totalPages = (int) Math.ceil((double) totalVideos / limit);
	        req.setAttribute("totalPages", totalPages);

	        req.getRequestDispatcher("/favorite.jsp").forward(req, resp);
	    }

}
