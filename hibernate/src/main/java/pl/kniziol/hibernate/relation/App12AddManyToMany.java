package pl.kniziol.hibernate.relation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.entity.Attribute;
import pl.kniziol.hibernate.entity.Product;

public class App12AddManyToMany {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final Product product = em.find(Product.class, 5L);
        //final Attribute attribute = em.find(Attribute.class, 1L);

        final Attribute attribute01 = new Attribute();
        attribute01.setValue("COLOR");
        attribute01.setName("YELLOW");

        product.addAttribute(attribute01);

        logger.info(product);
        em.getTransaction().commit();
        em.close();
    }
}
