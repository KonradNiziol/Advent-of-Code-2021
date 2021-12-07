package pl.kniziol.hibernate.crud;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Product;
import pl.kniziol.hibernate.entity.ProductType;

public class App01Create {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = new Product();
        product.setName("Rower 01");
        product.setDescription(" bla bla");
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        product.setProductType(ProductType.REAL);
        product.setPrice(BigDecimal.valueOf(10));

        em.persist(product);
        logger.info(product);

        em.getTransaction().commit();
        em.close();
    }
}
