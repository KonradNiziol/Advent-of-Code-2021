package pl.kniziol.hibernate.relation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Product;
import pl.kniziol.hibernate.entity.Review;

public class App07OneToManyDelete {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final Product product = em.find(Product.class, 2L);
        em.remove(product);

        em.getTransaction().commit();
        em.close();
    }
}
