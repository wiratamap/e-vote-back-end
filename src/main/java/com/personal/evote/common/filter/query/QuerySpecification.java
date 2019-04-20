package com.personal.evote.common.filter.query;

import com.personal.evote.common.filter.model.FilterAndSortRequestDTO;
import com.personal.evote.common.filter.model.FilterRequest;
import com.personal.evote.common.filter.model.SortRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class QuerySpecification<T> implements Specification<T> {

    private final transient FilterAndSortRequestDTO filterAndSortRequestDTO;

    public QuerySpecification(FilterAndSortRequestDTO filterAndSortRequestDTO) {
        this.filterAndSortRequestDTO = filterAndSortRequestDTO;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicateResult = criteriaBuilder.equal(criteriaBuilder.literal(true), true);
        List<Order> orders = new ArrayList<>();

        for (FilterRequest filterRequest : filterAndSortRequestDTO.getFilterRequests()) {
            predicateResult = filterRequest.getFilterOperator().buildQuery(root, criteriaBuilder, filterRequest, predicateResult);
        }

        for (SortRequest sortRequest : filterAndSortRequestDTO.getSortRequests()) {
            orders.add(sortRequest.getSortOrder().buildSortQuery(root, criteriaBuilder, sortRequest));
        }

        criteriaQuery.orderBy(orders);

        return predicateResult;
    }
}
