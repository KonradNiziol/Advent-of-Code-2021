package pl.kniziol.hibernate.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Product;

public class App01Edit {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        /*final Product product = em.find(Product.class, 1L);
        product.setName("Nowy Rower 01");*/
        //final Product merge = em.merge(product);
        final Product product = new Product();
        product.setId(1L);
        product.setName("New Product 02");
        final Product merge = em.merge(product);
        logger.info(product);

        em.getTransaction().commit();
        em.close();
    }
}
