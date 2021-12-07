package pl.kniziol.hibernate.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App23MultiMultiJoin {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final Query query = em.createQuery(
                "select distinct c.id, c.lastNAme as customer, ca.name as category, SUM(orw.price) from Customer c " +
                        "inner join c.orders o " +
                        "inner join o.orderRows orw " +
                        "inner join orw.product p " +
                        "inner join p.category ca group by ca, c");

        final List<Object[]> resultList = query.getResultList();
        for (Object[] object : resultList) {
            logger.info(object[0] + " ," + object[1] + " ," + object[2] + " ," + object[3] + " ,");
        }

        em.getTransaction().commit();
        em.close();
    }
}
