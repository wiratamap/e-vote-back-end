package com.personal.evote.common.filter.enumeration;


import com.personal.evote.common.filter.model.SortRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public enum SortOrder {
    ASCENDING {
        @Override
        public <T> Order buildSortQuery(Root<T> root, CriteriaBuilder criteriaBuilder, SortRequest sortRequest) {
            return criteriaBuilder.asc(root.get(sortRequest.getKey()));
        }
    },
    DESCENDING {
        @Override
        public <T> Order buildSortQuery(Root<T> root, CriteriaBuilder criteriaBuilder, SortRequest sortRequest) {
            return criteriaBuilder.desc(root.get(sortRequest.getKey()));
        }
    },
    ;

    public abstract <T> Order buildSortQuery(Root<T> root, CriteriaBuilder criteriaBuilder, SortRequest sortRequest);
}
