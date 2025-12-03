package poly.com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.dao.UserDAO;
import poly.com.model.User;
import utils.XJpa;
public class UserDAOImpl implements UserDAO{

	 @Override
	    public List<User> findAll() {
	        EntityManager em = XJpa.getEntityManager();
	        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
	        return query.getResultList();
	    }

	    @Override
	    public User findById(String id) {
	        EntityManager em = XJpa.getEntityManager();
	        return em.find(User.class, id);
	    }

	    @Override
	    public void create(User user) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(user);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw e;
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public void update(User user) {
	        EntityManager em = XJpa.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.merge(user);
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
	            User user = em.find(User.class, id);
	            if (user != null) {
	                em.remove(user);
	            }
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw e;
	        } finally {
	            em.close();
	        }
	    }
	    
	    //check login
	    
	    public User checkLogin(String username, String password) {
	        try {
	            EntityManager em = XJpa.getEntityManager();

	            TypedQuery<User> query = em.createQuery(
	                "SELECT u FROM User u WHERE u.id = :id AND u.password = :password",
	                User.class
	            );

	            query.setParameter("id", username);
	            query.setParameter("password", password);

	            return query.getSingleResult(); // Nếu không có sẽ ném Exception
	        } catch (Exception e) {
	            return null;
	        }
	    }

}
