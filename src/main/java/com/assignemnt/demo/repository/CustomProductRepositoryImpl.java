package com.assignemnt.demo.repository;

import com.assignemnt.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomProductRepositoryImpl implements CustomProductRepository {
    private EntityManager entityManager;

    public CustomProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<Product> getAllProductByFilter(Map<String, String> filters, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> product = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        String name = filters.get("name");
        String brand = filters.get("brand");
        String color = filters.get("color");

        if (!StringUtils.isEmpty(name)) {
            predicates.add(cb.like(product.get("name"), "%" + name + "%"));
        }
        if (!StringUtils.isEmpty(color)) {
            predicates.add(cb.equal(product.get("color"), color));
        }
        if (!StringUtils.isEmpty(brand)) {
            predicates.add(cb.equal(product.get("brand"), brand));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Product> query = entityManager.createQuery(cq).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize());
        List<Product> resultList = query.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Product> productRootCount = countQuery.from(Product.class);
        countQuery.select(cb.count(productRootCount)).where(cb.and(predicates.toArray(new Predicate[0])));

        // Fetches the count of all Books as per given criteria
        Long count = entityManager.createQuery(countQuery).getSingleResult();
        Page<Product> page = new PageImpl<>(resultList, pageable, count);

        return page;
    }
}
