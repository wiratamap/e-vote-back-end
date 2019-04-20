package com.personal.evote.lookup.candidate.service;

import com.personal.evote.lookup.candidate.model.Candidate;
import com.personal.evote.lookup.candidate.repository.CandidateRepository;
import com.personal.evote.lookup.candidatecategory.model.CandidateCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceTest {

    @InjectMocks
    private CandidateService candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    private Candidate availableCandidate() {
        CandidateCategory candidateCategory = CandidateCategory.builder()
                .name("Presidential")
                .description("Executive")
                .build();

        return Candidate.builder()
                .candidateNumber(1)
                .candidateCategory(candidateCategory)
                .build();
    }

    @Test
    public void fetch_expectReturnCandidate_whenCandidateDataIsAvailable() {
        UUID availableCandidateId = UUID.randomUUID();
        Candidate expectedCandidate = availableCandidate();

        Mockito.when(candidateRepository.findById(availableCandidateId)).thenReturn(Optional.ofNullable(expectedCandidate));

        Candidate availableCandidate = candidateService.fetch(availableCandidateId);

        assertEquals(expectedCandidate, availableCandidate);
    }

    @Test
    public void fetch_expectReturnNull_whenCandidateDataIsNotAvailable() {
        UUID candidateToBeSearch = UUID.randomUUID();

        Mockito.when(candidateRepository.findById(candidateToBeSearch)).thenReturn(Optional.empty());

        Candidate availableCandidate = candidateService.fetch(candidateToBeSearch);

        assertNull(availableCandidate);
    }
}
