package com.personal.evote.common.filter.model;

import com.personal.evote.common.filter.enumeration.FilterOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequest {
    private String key;
    private Object value;
    private FilterOperator filterOperator;
}
