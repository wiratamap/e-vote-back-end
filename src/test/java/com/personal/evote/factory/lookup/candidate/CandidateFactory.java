package com.personal.evote.factory.lookup.candidate;

import com.personal.evote.factory.lookup.candidatecategory.CandidateCategoryFactory;
import com.personal.evote.lookup.candidate.model.Candidate;

public class CandidateFactory {

    private static Candidate candidate;

    public static CandidateFactory construct() {
        candidate = Candidate.builder()
                .candidateNumber(1)
                .candidateCategory(CandidateCategoryFactory.construct().get())
                .build();

        return new CandidateFactory();
    }

    public Candidate get() {
        return candidate;
    }
}
