package pl.kniziol.hibernate.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Product;
import pl.kniziol.hibernate.entity.Review;

public class App17jpql {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //final Product product = em.find(Product.class, 5L);

        //final TypedQuery<Product> query = em.createQuery("select p from Product p where p.name like '%04'", Product.class);
        //final TypedQuery<Product> query = em.createQuery("select p from Product p where p.id = :id", Product.class);
        final Query query = em.createQuery("select AVG(p.price) from Product p");
        final Double singleResult = (Double) query.getSingleResult();
        logger.info(singleResult);

        //query.setParameter("id", 6L);

        //final List<Product> resultList = query.getResultList();
        /*for (Product product : resultList) {
            logger.info(product);
        }*/
        /*try {
            final Product singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            logger.error("Brak wyniku", e);
        }*/

        /*query.getResultStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Brak wynikow"));*/


        //logger.info(singleResult);

        final Query query2 = em.createQuery("select p.category.id, COUNT(p) from Product p GROUP BY p.category");
        final List<Object[]> resultList = query2.getResultList();

        for (Object[] objects : resultList) {
            logger.info(objects[0] + ", " + objects[1]);
        }

        em.getTransaction().commit();
        em.close();
    }
}
