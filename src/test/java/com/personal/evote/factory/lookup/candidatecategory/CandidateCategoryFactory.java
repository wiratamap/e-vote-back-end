package com.personal.evote.factory.lookup.candidatecategory;

import com.personal.evote.lookup.candidatecategory.model.CandidateCategory;

import java.util.UUID;

public class CandidateCategoryFactory {

    private static CandidateCategory candidateCategory;

    public static CandidateCategoryFactory construct() {
        candidateCategory = CandidateCategory.builder()
                .name("Presidential")
                .description("Executive")
                .build();
        candidateCategory.setId(UUID.randomUUID());

        return new CandidateCategoryFactory();
    }

    public CandidateCategory get() {
        return candidateCategory;
    }
}
