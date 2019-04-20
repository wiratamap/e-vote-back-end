package com.personal.evote.common.filter.enumeration;


import com.personal.evote.common.filter.model.FilterRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public enum FilterOperator {
    EQUAL {
        @Override
        public <T> Predicate buildQuery(Root<T> root, CriteriaBuilder criteriaBuilder, FilterRequest filterRequest, Predicate lastPredicate) {
            return criteriaBuilder.and(criteriaBuilder.equal(root.get(filterRequest.getKey()), filterRequest.getValue().toString()), lastPredicate);
        }
    },
    LIKE {
        @Override
        public <T> Predicate buildQuery(Root<T> root, CriteriaBuilder criteriaBuilder, FilterRequest filterRequest, Predicate lastPredicate) {
            return criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get(filterRequest.getKey())), "%" + filterRequest.getValue().toString().toUpperCase() + "%"), lastPredicate);
        }
    };

    public abstract <T> Predicate buildQuery(Root<T> root, CriteriaBuilder criteriaBuilder, FilterRequest filterRequest, Predicate lastPredicate);
}
