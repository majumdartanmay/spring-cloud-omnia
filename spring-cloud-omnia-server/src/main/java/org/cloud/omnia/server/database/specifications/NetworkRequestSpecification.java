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

public class NetworkRequestSpecification
        implements Specification<NetworkRequestEntity> {

    /**
     * filters provided for READ operations.
     */
    private OmniaSearchFilters[] filters;

    /**
     * Contructor.
     * @param omniaSearchFilters filter provided for READ operations.
     */
    public NetworkRequestSpecification(
            final OmniaSearchFilters[] omniaSearchFilters) {
        this.filters = omniaSearchFilters;
    }

    private Predicate processCriteria(
                  final Root<NetworkRequestEntity> root,
                  final CriteriaBuilder builder,
                  final OmniaSearchFilters.SubSearchFilters criteria) {

        switch (criteria.getOperation()) {
            case ">":
                return builder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue());
            case "<":
                return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue());
            case ":":
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(
                    root.get(criteria.getKey()),
                            "%" + criteria.getValue() + "%");
                } else {
                    return builder.equal(
                            root.get(criteria.getKey()), criteria.getValue());
                }
            case "<>":
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.notLike(
                            root.get(criteria.getKey()),
                            "%" + criteria.getValue() + "%");
                } else {
                    return builder.notEqual(root.get(criteria.getKey()),
                            criteria.getValue());
                }
            default:
                return null;
        }

    }

    /**
     * Function to convert custom query to predicate.
     * @param root Holds the entity related data.
     * @param query
     * @param builder Used to perform required operations on the query.
     * @return Predicate which wil act as a filer.
     */
    @Override
    public Predicate toPredicate(
            final Root<NetworkRequestEntity> root,
            final CriteriaQuery<?> query,
            final CriteriaBuilder builder) {

        if (filters == null || filters.length == 0) {
            return null;
        }

        List<Predicate> finalPredicate = new ArrayList<>();
        for (OmniaSearchFilters filter : filters) {

            OmniaSearchFilters.SubSearchFilters[]
                    searchFilters = filter.getFilters();

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
                    default:
                        throw new RuntimeException(
                                String.format("Invalid operator %s",
                                filter.getBaseOperator()));
                }
            }
        }

        Predicate[] interimPredicates = new Predicate[finalPredicate.size()];
        interimPredicates = finalPredicate.toArray(interimPredicates);

        return builder.and(interimPredicates);
    }

}
