package pl.kniziol.hibernate.relation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Attribute;
import pl.kniziol.hibernate.entity.Product;
import pl.kniziol.hibernate.entity.Review;

public class App13AddOneToMany {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final Product product = em.find(Product.class, 5L);
        final Review review = new Review();
        review.setContent("xxx");
        review.setRating(5);
        product.addReview(review);
        //review.setProduct(product);

        logger.info(product);
        em.getTransaction().commit();
        em.close();
    }
}
