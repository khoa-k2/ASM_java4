package poly.com.controller;


import java.io.IOException;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import poly.com.model.Video;
import utils.XJpa;


@WebServlet("/update-video")
public class UpdateVideoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            String title = req.getParameter("title");
            String link = req.getParameter("link");
            String poster = req.getParameter("poster");
            String description = req.getParameter("description");

            EntityManager em = XJpa.getEntityManager();

            Video video = em.find(Video.class, id);
            if (video == null) {
                req.setAttribute("message", "Không tìm thấy video để cập nhật!");
                req.getRequestDispatcher("/manager.jsp").forward(req, resp);
                return;
            }

            em.getTransaction().begin();
            video.setTitle(title);
            video.setLink(link);
            video.setPoster(poster);
            video.setDescription(description);
            em.getTransaction().commit();

            req.setAttribute("message", "Cập nhật video thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi cập nhật: " + e.getMessage());
        }

        req.getRequestDispatcher("/manager").forward(req, resp);
    }
}
