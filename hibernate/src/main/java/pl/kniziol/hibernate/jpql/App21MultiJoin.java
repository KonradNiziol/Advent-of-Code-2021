package pl.kniziol.hibernate.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Product;

public class App21MultiJoin {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final Query query2 = em.createQuery(
                "select c from Category c left join fetch c.product p left join fetch p.reviews where c.id=:id", Product.class);
        //org.hibernate.loader.MultipleBagFetchException -> hibernate nie pozwala pobrac kilku list w jednym zapytaniu (rozwiazaniem moze nyć zamiana List na Set
        // jednak efektem ubocznym jest wynik kartezjanski)

        query2.setParameter("id", 1L);
        final List<Product> results = query2.getResultList();
        for (Product product : results) {
            logger.info(product);
            logger.info(product.getCategory());
        }


        em.getTransaction().commit();
        em.close();
    }
}
