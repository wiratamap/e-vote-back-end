package com.personal.evote.lookup.candidate.service;

import com.personal.evote.factory.lookup.candidate.CandidateFactory;
import com.personal.evote.lookup.candidate.exception.CandidateNotFoundException;
import com.personal.evote.lookup.candidate.model.Candidate;
import com.personal.evote.lookup.candidate.repository.CandidateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceTest {

    @InjectMocks
    private CandidateService candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @Test
    public void fetch_expectReturnCandidate_whenCandidateDataIsAvailable() {
        UUID availableCandidateId = UUID.randomUUID();
        Candidate expectedCandidate = CandidateFactory.construct().get();

        Mockito.when(candidateRepository.findById(availableCandidateId)).thenReturn(Optional.ofNullable(expectedCandidate));

        Candidate availableCandidate = candidateService.fetch(availableCandidateId);

        assertEquals(expectedCandidate, availableCandidate);
    }

    @Test(expected = CandidateNotFoundException.class)
    public void fetch_expectThrowException_whenCandidateDataIsNotAvailable() {
        UUID candidateToBeSearch = UUID.randomUUID();

        Mockito.when(candidateRepository.findById(candidateToBeSearch)).thenReturn(Optional.empty());

        candidateService.fetch(candidateToBeSearch);
    }
}
