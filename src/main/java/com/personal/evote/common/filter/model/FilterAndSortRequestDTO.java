package com.personal.evote.common.filter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterAndSortRequestDTO {
    private List<FilterRequest> filterRequests;
    private List<SortRequest> sortRequests;
}
