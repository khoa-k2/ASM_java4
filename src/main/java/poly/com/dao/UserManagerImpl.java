package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.model.User;
import utils.XJpa;

public class UserManagerImpl implements UserDAO {
	EntityManager em = XJpa.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close();
	}

	@Override
	public List<User> findAll() {
		String jpql = "SELECT o FROM User o";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		return query.getResultList();
	}

	@Override
	public User findById(String id) {
		return em.find(User.class, id);
	}

	@Override
	public void create(User entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(User entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void deleteById(String id) {
		User entity = em.find(User.class, id);
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	//phan trang
	public List<User> findByPage(int pageNumber, int pageSize) {
	    String jpql = "SELECT o FROM User o";
	    TypedQuery<User> query = em.createQuery(jpql, User.class);
	    
	    // Thiết lập vị trí bắt đầu (offset) và số lượng kết quả tối đa
	    query.setFirstResult(pageNumber * pageSize);
	    query.setMaxResults(pageSize);
	    
	    return query.getResultList();
	}
	
	public long count() {
	    String jpql = "SELECT COUNT(o) FROM User o";
	    return em.createQuery(jpql, Long.class).getSingleResult();
	}

}
