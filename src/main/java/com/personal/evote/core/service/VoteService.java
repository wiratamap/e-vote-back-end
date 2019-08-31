package com.personal.evote.core.service;

import com.personal.evote.core.exception.IllegalVoterException;
import com.personal.evote.core.model.RunningVote;
import com.personal.evote.core.model.dto.VoteDto;
import com.personal.evote.core.repository.RunningVoteRepository;
import com.personal.evote.lookup.appuser.model.ApplicationUser;
import com.personal.evote.lookup.appuser.service.ApplicationUserService;
import com.personal.evote.lookup.candidate.model.Candidate;
import com.personal.evote.lookup.candidate.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final RunningVoteRepository runningVoteRepository;

    private final CandidateService candidateService;

    private final ApplicationUserService applicationUserService;

    public RunningVote vote(VoteDto voteDto) {
        String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        ApplicationUser existingApplicationUser = applicationUserService.fetchByUsername(authenticatedUser);
        Candidate existingCandidate = candidateService.fetch(voteDto.getCandidateId());

        Optional<List<RunningVote>> existingRunningVotes = runningVoteRepository
                .findAllByCandidateCategoryIdAndVoterId(existingCandidate.getCandidateCategory().getId(), existingApplicationUser.getId());

        if (existingRunningVotes.isPresent()) {
            throw new IllegalVoterException();
        }

        RunningVote runningVote = RunningVote.builder()
                .voterId(existingApplicationUser.getId())
                .candidateId(existingCandidate.getId())
                .candidateCategoryId(existingCandidate.getCandidateCategory().getId())
                .build();

        return runningVoteRepository.save(runningVote);
    }
}
