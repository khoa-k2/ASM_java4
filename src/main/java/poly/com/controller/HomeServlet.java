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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	VideoDAOImpl VideoDAO = new VideoDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int pageSize = 6;

		String pageParam = req.getParameter("page");
		if (pageParam != null) {
			page = Integer.parseInt(pageParam);
		}

		int offset = (page - 1) * pageSize;
		List<Video> videos = VideoDAO.getTopVideos(pageSize, offset);
		long totalVideos = VideoDAO.countVideos();
		int totalPages = (int) Math.ceil((double) totalVideos / pageSize);

		req.setAttribute("videos", videos);
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPages", totalPages);
		
		System.out.println("videos size = " + videos.size());
		for(Video v: videos){
		    System.out.println(v.getId() + " - " + v.getTitle());
		}
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
