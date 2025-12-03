package poly.com.dao;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.dao.FavoriteDAO;
import poly.com.model.Favorite;
import utils.XJpa;

public class FavoriteDAOImpl implements FavoriteDAO {

	 @Override
	    public List<Favorite> findAll() {
	        EntityManager em =XJpa.getEntityManager();
	        TypedQuery<Favorite> query = em.createQuery("SELECT f FROM Favorite f", Favorite.class);
	        return query.getResultList();
	    }

	    @Override
	    public Favorite findById(Integer id) {
	        EntityManager em = XJpa.getEntityManager();
	        return em.find(Favorite.class, id);
	    }

	    @Override
	    public void create(Favorite favorite) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(favorite);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw e;
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public void update(Favorite favorite) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.merge(favorite);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw e;
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public void deleteById(Integer id) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            Favorite f = em.find(Favorite.class, id);
	            if (f != null) {
	                em.remove(f);
	            }
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw e;
	        } finally {
	            em.close();
	        }
	    }

}
