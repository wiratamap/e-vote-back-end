package com.personal.evote.core.service;

import com.personal.evote.core.model.RunningVote;
import com.personal.evote.core.model.dto.VoteDto;
import com.personal.evote.core.repository.RunningVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final RunningVoteRepository runningVoteRepository;

    RunningVote vote(VoteDto voteDto) {
        RunningVote runningVote = RunningVote.builder()
                .voterId(voteDto.getVoterId())
                .candidateId(voteDto.getCandidateId())
                .build();

        return runningVoteRepository.save(runningVote);
    }
}
