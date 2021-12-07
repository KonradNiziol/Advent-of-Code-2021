package pl.kniziol.hibernate.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Order;

public class App26NPlusOne {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //final Order order = em.find(Order.class, 1L);
        final List<Order> orders = em.createQuery(
                "select distinct o from Order o", Order.class)
                .getResultList();

        for (Order order : orders) {
            logger.info(order);
            logger.info(order.getOrderRows());
        }

        em.getTransaction().commit();
        em.close();
    }
}
