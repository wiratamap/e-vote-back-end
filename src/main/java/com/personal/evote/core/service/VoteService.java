package com.personal.evote.core.service;

import com.personal.evote.core.exception.IllegalVoterException;
import com.personal.evote.core.model.RunningVote;
import com.personal.evote.core.model.dto.VoteDto;
import com.personal.evote.core.repository.RunningVoteRepository;
import com.personal.evote.lookup.candidate.model.Candidate;
import com.personal.evote.lookup.candidate.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final RunningVoteRepository runningVoteRepository;

    private final CandidateService candidateService;

    public RunningVote vote(VoteDto voteDto) {
        Candidate existingCandidate = candidateService.fetch(voteDto.getCandidateId());

        Optional<List<RunningVote>> existingRunningVotes = runningVoteRepository.findAllByCandidateCategoryId(existingCandidate.getCandidateCategory().getId());

        if (existingRunningVotes.isPresent()) {
            throw new IllegalVoterException();
        }

        RunningVote runningVote = RunningVote.builder()
                .voterId(voteDto.getVoterId())
                .candidateId(existingCandidate.getId())
                .candidateCategoryId(existingCandidate.getCandidateCategory().getId())
                .build();

        return runningVoteRepository.save(runningVote);
    }
}
