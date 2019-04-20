package com.personal.evote.common.filter.model;

import com.personal.evote.common.filter.enumeration.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortRequest implements Serializable {
    private String key;
    private SortOrder sortOrder;
}
