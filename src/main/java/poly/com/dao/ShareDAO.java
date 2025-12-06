package poly.com.dao;

import jakarta.persistence.EntityManager;
import poly.com.model.Share;
import utils.XJpa;

public class ShareDAO {

	public void save(Share share) {
        EntityManager em = XJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(share);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}
