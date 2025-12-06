package poly.com.dao;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import poly.com.dao.VideoDAO;
import poly.com.model.Video;
import utils.XJpa;

public class VideoDAOImpl implements VideoDAO{
	 @Override
	    public List<Video> findAll() {
	        EntityManager em = XJpa.getEntityManager();
	        TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
	        return query.getResultList();
	    }

	    @Override
	    public Video findById(String id) {
	        EntityManager em = XJpa.getEntityManager();
	        return em.find(Video.class, id);
	    }

	    @Override
	    public void create(Video video) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(video);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw e;
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public void update(Video video) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.merge(video);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw e;
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public void deleteById(String id) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            Video video = em.find(Video.class, id);
	            if (video != null) {
	                em.remove(video);
	            }
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw e;
	        } finally {
	            em.close();
	        }
	    }
	///
	    
	    
	    public List<Video> getTopVideos(int limit, int offset) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v ORDER BY views DESC", Video.class);
	            query.setFirstResult(offset);
	            query.setMaxResults(limit);
	            return query.getResultList();
	        } finally {
	            em.close();
	        }
	    }

	 
	    public int countAll() {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            Long total = em.createQuery("SELECT COUNT(v) FROM Video v", Long.class)
	                           .getSingleResult();
	            return total.intValue();
	        } finally {
	            em.close();
	        }
	    }
	    
	    public long countVideos() {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            return em.createQuery("SELECT COUNT(v) FROM Video v", Long.class).getSingleResult();
	        } finally {
	            em.close();
	        }
	    }

	 // Tìm video theo từ khóa trong title
	    public List<Object[]> searchByKeyword(String keyword) {
	    	EntityManager em = XJpa.getEntityManager();
	        String jpql = """
	            SELECT v.title, COUNT(f.id)
	            FROM Video v LEFT JOIN v.favorites f
	            WHERE v.title LIKE :kw
	            GROUP BY v.id, v.title
	            ORDER BY v.title
	        """;

	        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	        query.setParameter("kw", "%" + keyword + "%");

	        return query.getResultList();
	    }
		
	    //tăng views
	    public void increaseView(String id) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            em.getTransaction().begin();

	            em.createQuery("UPDATE Video v SET v.views = v.views + 1 WHERE v.id = :id")
	              .setParameter("id", id)
	              .executeUpdate();

	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }

}
