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

public class App18jpqlAResponseMapping {

    private static Logger logger = LogManager.getLogger();
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        final Query query = em.createQuery("select p.category.id, COUNT(p) from Product p GROUP BY p.category");
        final List<Object[]> resultList = query.getResultList();
        final List<ProductInCategoryCounterDto> results = resultList.stream()
                .map(objects -> new ProductInCategoryCounterDto((Long) objects[0], (Long) objects[1]))
                .collect(Collectors.toList());
        for (ProductInCategoryCounterDto dto : results) {
            logger.info(dto.getCategoryId() + ", " + dto.getProductInCategoryCounter());
        }



        em.getTransaction().commit();
        em.close();
    }
}
