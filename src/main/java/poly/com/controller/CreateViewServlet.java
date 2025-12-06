package poly.com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.VideoDAOImpl;
import poly.com.model.Video;
@WebServlet("/video/view")
public class CreateViewServlet extends HttpServlet{


    private VideoDAOImpl videoDAO = new VideoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id != null) {
            // Tăng lượt xem
            videoDAO.increaseView(id);;

            // Lấy lại video để hiển thị
            Video video = videoDAO.findById(id);
            req.setAttribute("video", video);

            // Điều hướng đến trang chi tiết video
            req.getRequestDispatcher("/views/video-detail.jsp").forward(req, resp);
        }
    }

}
