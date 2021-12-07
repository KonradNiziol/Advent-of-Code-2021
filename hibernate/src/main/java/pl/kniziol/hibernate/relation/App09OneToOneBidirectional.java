package pl.kniziol.hibernate.relation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Category;
import pl.kniziol.hibernate.entity.Product;

public class App09OneToOneBidirectional {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final Category category = em.find(Category.class, 1L);

        logger.info(category);
        logger.info(category.getProduct());

        em.getTransaction().commit();
        em.close();
    }
}
