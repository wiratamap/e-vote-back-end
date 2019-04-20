package com.personal.evote.lookup.candidate.service;

import com.personal.evote.lookup.candidate.exception.CandidateNotFoundException;
import com.personal.evote.lookup.candidate.model.Candidate;
import com.personal.evote.lookup.candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public Candidate fetch(UUID candidateToBeSearch) {
        return candidateRepository.findById(candidateToBeSearch)
                .orElseThrow(CandidateNotFoundException::new);
    }
}
