package com.personal.evote.core.service;

import com.personal.evote.core.model.RunningVote;
import com.personal.evote.core.model.dto.VoteDto;
import com.personal.evote.core.repository.RunningVoteRepository;
import com.personal.evote.lookup.candidate.model.Candidate;
import com.personal.evote.lookup.candidate.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final RunningVoteRepository runningVoteRepository;

    private final CandidateService candidateService;

    RunningVote vote(VoteDto voteDto) {
        Candidate existingCandidate = candidateService.fetch(voteDto.getCandidateId());

        RunningVote runningVote = RunningVote.builder()
                .voterId(voteDto.getVoterId())
                .candidateId(existingCandidate.getId())
                .build();

        return runningVoteRepository.save(runningVote);
    }
}
