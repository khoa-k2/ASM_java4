package poly.com.controller;

import java.io.IOException;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.model.Video;
import utils.XJpa;

@WebServlet("/edit-video")
public class EditVideoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        EntityManager em = XJpa.getEntityManager();
        Video video = em.find(Video.class, id);

        if (video == null) {
            req.setAttribute("message", "Không tìm thấy video có ID: " + id);
        } else {
            req.setAttribute("video", video);
        }

        req.getRequestDispatcher("/edit-video.jsp").forward(req, resp);
    }
}