package pl.kniziol.hibernate.relation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Category;
import pl.kniziol.hibernate.entity.Product;

public class App11AddOneToOne {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final Product product = em.find(Product.class, 3L);
        //final Category category = em.find(Category.class, 2L);
        final Category category = new Category();
        category.setName("Nowa");
        category.setDescription("dasfsdmauf kjy gafsdf");
        em.persist(category);
        product.setCategory(category);

        logger.info(product);
        em.getTransaction().commit();
        em.close();
    }
}
