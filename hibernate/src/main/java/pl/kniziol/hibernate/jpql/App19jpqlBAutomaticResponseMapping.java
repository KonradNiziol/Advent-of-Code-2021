package pl.kniziol.hibernate.jpql;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.kniziol.hibernate.dto.ProductInCategoryCounterDto;

public class App19jpqlBAutomaticResponseMapping {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final Query query2 = em.createQuery("select new pl.kniziol.hibernate.dto.ProductInCategoryCounterDto(p.category.id, COUNT(p))"
                + " from Product p GROUP BY p.category");
        final List<ProductInCategoryCounterDto> resultList = query2.getResultList();

        for (ProductInCategoryCounterDto objects : resultList) {
            logger.info(objects.getCategoryId() + ", " + objects.getProductInCategoryCounter());
        }


        em.getTransaction().commit();
        em.close();
    }
}
