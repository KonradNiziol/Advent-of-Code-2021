package pl.kniziol.hibernate.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.dto.ProductInCategoryCounterDto;
import pl.kniziol.hibernate.entity.Product;

public class App20jpqlJoin {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        /*final Query query2 = em.createQuery("select p, c.name from Product p inner join p.category c where c.id=:id", Product.class);
        query2.setParameter("id", 1L);
        final List<Product> results = query2.getResultList();
        for (Product product : results) {
            logger.info(product);
            logger.info(product.getCategory());
        }*/

        /*final Query query2 = em.createQuery("select p from Product p inner join fetch p.category c where c.id=:id", Product.class);
        query2.setParameter("id", 1L);
        final List<Product> results = query2.getResultList();
        for (Product product : results) {
            logger.info(product);
            logger.info(product.getCategory());
        }*/

        final Query query2 = em.createQuery("select p from Product p left join p.category c", Product.class);
        //query2.setParameter("id", 1L);
        final List<Product> results = query2.getResultList();
        for (Product product : results) {
            logger.info(product);
            logger.info(product.getCategory());
        }

        em.getTransaction().commit();
        em.close();
    }
}
