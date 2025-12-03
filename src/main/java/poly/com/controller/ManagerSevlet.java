package poly.com.controller;

import java.io.IOException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.model.Video;
import utils.XJpa;
@WebServlet("/manager")
public class ManagerSevlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        EntityManager em = XJpa.getEntityManager();

        // Lấy danh sách video
        TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
        List<Video> videos = query.getResultList();

        req.setAttribute("videos", videos);

        // Trả về trang quản lý video
        req.getRequestDispatcher("/manager.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            // Nhận dữ liệu từ form
            String id = req.getParameter("id");
            String title = req.getParameter("title");
            String link = req.getParameter("link");
            String poster = req.getParameter("poster");
            String description = req.getParameter("description");

            // Nếu chưa có poster → tự tạo poster bằng API YouTube
            if (poster == null || poster.isEmpty()) {
                String videoId = getYoutubeId(link);
                if (videoId != null) {
                    poster = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
                }
            }

            // Tạo đối tượng video
            Video video = Video.builder()
                    .id(id)
                    .title(title)
                    .link(link)
                    .poster(poster)
                    .description(description)
                    .build();

            // Lưu vào DB
            EntityManager em = XJpa.getEntityManager();
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();

            req.setAttribute("message", "Thêm video thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi thêm video: " + e.getMessage());
        }

        // Load lại danh sách video
        doGet(req, resp);
    }

    // ---------------------------
    // HÀM LẤY ID TỪ LINK YOUTUBE
    // ---------------------------
    private String getYoutubeId(String url) {
        if (url == null) return null;

        // Dạng: https://www.youtube.com/watch?v=ABCDE123
        if (url.contains("v=")) {
            return url.substring(url.indexOf("v=") + 2);
        }

        // Dạng: https://youtu.be/ABCDE123
        if (url.contains("youtu.be/")) {
            return url.substring(url.lastIndexOf("/") + 1);
        }

        return null;
    }
}
