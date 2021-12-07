package pl.kniziol.hibernate.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.QueryHint;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;

import pl.kniziol.hibernate.entity.Product;

public class App22CartesianProduct {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        /*final TypedQuery<Product> query = em.createQuery(
                "select p from Product p left join fetch p.attributes left join fetch p.reviews", Product.class);*/
        // Dostajemy 125 wynikow (5 produktow, 5 atrybutow kazdy i 5 opini kazdy 5*5*5)
        // Aby sie pozbyc mozemy pozbyc sie 1 joina i pozwolic aby hibernate doczytywal (lazy loading)) -> 25
        // mozemy tez dodac 'distinct' aby pozbyc sie duplikatu

        /*final TypedQuery<Product> query = em.createQuery(
                "select distinct p from Product p left join fetch p.attributes", Product.class);*/
        // w takim przypadku przy iteracji w for i wypisywaniu review mamy problem n+1

        List<Product> resultList = em.createQuery("select distinct p from Product p left join fetch p.attributes",
                Product.class).getResultList();

        resultList = em.createQuery("select distinct p from Product p left join fetch p.reviews",
                Product.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        //slowo distinct w tym przypadku uzywane jest tylko przez hibernate poniewaz w MySQL to słowko nie dziala jak listujemy parametry w zapytaniu
        // tworzy to pewien narzut na baze dlatego mozemy dodac .setHint

        logger.info(resultList.size());

        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getAttributes());
            logger.info(product.getReviews());
        }


        em.getTransaction().commit();
        em.close();
    }
}
