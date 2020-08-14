package org.cloud.omnia.server.database.specifications;

import database.OmniaSearchFilters;
import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkRequestSpecification implements Specification<NetworkRequestEntity> {

    private OmniaSearchFilters[] filters;

    public NetworkRequestSpecification(OmniaSearchFilters[] filters){
        this.filters = filters;
    }

    private Predicate processCriteria(Root<NetworkRequestEntity> root,
                                      CriteriaBuilder builder,
                                      OmniaSearchFilters.SubSearchFilters criteria) {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }else if(criteria.getOperation().equals("<>")){
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.notLike(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }

    @Override
    public Predicate toPredicate(Root<NetworkRequestEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (filters == null || filters.length == 0) {
            return null;
        }

        List<Predicate> finalPredicate = new ArrayList<>();
        for (OmniaSearchFilters filter : filters) {

            OmniaSearchFilters.SubSearchFilters[] searchFilters = filter.getFilters();

            Predicate[] tempPredicate = Arrays.stream(searchFilters)
                    .map(x -> processCriteria(root, builder, x))
                    .toArray(Predicate[]::new);

            if (tempPredicate.length != 0) {
                switch (filter.getBaseOperator()) {
                    case "AND":
                        finalPredicate.add(builder.and(tempPredicate));
                        break;
                    case "OR":
                        finalPredicate.add(builder.or(tempPredicate));
                        break;
                }
            }
        }

        Predicate[] interimPredicates = new Predicate[finalPredicate.size()];
        interimPredicates = finalPredicate.toArray(interimPredicates);

        return builder.and(interimPredicates);
    }

}
