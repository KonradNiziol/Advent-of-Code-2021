package pl.kniziol.hibernate.jpql;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Order;

public class App25QueryWithIn {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //final Order order = em.find(Order.class, 1L);
        final List<Order> orders = em.createQuery(
                "select o from Order o"
                        + " where o.id in (:ids)", Order.class)
                .setParameter("ids", Arrays.asList(1L, 3L, 5L))
                .getResultList();



        for (Order order : orders) {
            logger.info(order);
        }

        em.getTransaction().commit();
        em.close();
    }
}
