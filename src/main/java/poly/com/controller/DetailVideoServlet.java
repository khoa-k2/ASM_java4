package poly.com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.VideoDAOImpl;
import poly.com.model.Video;
@WebServlet("/videoDetail")
public class DetailVideoServlet extends HttpServlet{
 private VideoDAOImpl DAO = new VideoDAOImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  String id = req.getParameter("id");

		  if (id == null || id.isEmpty()) {
	            req.setAttribute("error", "Không tìm thấy ID video!");
	            req.getRequestDispatcher("/video-detail.jsp").forward(req, resp);
	            return;
	        }

	        Video video = DAO.findById(id); // Dùng hàm bạn muốn dùng

	        if (video == null) {
	            req.setAttribute("error", "Video không tồn tại!");
	        } else {
	            req.setAttribute("video", video);
	        }

	        req.getRequestDispatcher("/video-detail.jsp").forward(req, resp);
	    }
	}


