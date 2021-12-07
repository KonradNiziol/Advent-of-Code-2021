package pl.kniziol.hibernate.relation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Product;

public class App05OneToMany {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final List<Product> products = em.createQuery("select p from Product p").getResultList();
        for (Product product : products) {
            logger.info(product.getName());
            logger.info(product.getReviews());
        }

        em.getTransaction().commit();
        em.close();
    }
}
