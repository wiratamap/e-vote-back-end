package com.personal.evote.core.service;

import com.personal.evote.core.exception.IllegalVoterException;
import com.personal.evote.core.model.RunningVote;
import com.personal.evote.core.model.dto.VoteDto;
import com.personal.evote.core.repository.RunningVoteRepository;
import com.personal.evote.factory.core.RunningVoteFactory;
import com.personal.evote.factory.lookup.appuser.ApplicationUserFactory;
import com.personal.evote.factory.lookup.candidate.CandidateFactory;
import com.personal.evote.lookup.appuser.model.ApplicationUser;
import com.personal.evote.lookup.appuser.service.ApplicationUserService;
import com.personal.evote.lookup.candidate.exception.CandidateNotFoundException;
import com.personal.evote.lookup.candidate.model.Candidate;
import com.personal.evote.lookup.candidate.service.CandidateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @InjectMocks
    private VoteService voteService;

    @Mock
    private RunningVoteRepository runningVoteRepository;

    @Mock
    private CandidateService candidateService;

    @Mock
    private ApplicationUserService applicationUserService;

    @Before
    public void setUp() {
        Mockito.when(runningVoteRepository.save(any(RunningVote.class))).then(returnsFirstArg());

        ApplicationUser applicationUser = ApplicationUserFactory.construct().get();
        Authentication authentication = new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void vote_expectReturnSavedRunningVote_whenCandidateIsValid() {
        Candidate existingCandidate = CandidateFactory.construct().get();
        ApplicationUser applicationUser = ApplicationUserFactory.construct().get();
        UUID candidateId = existingCandidate.getId();
        VoteDto voteDto = new VoteDto(candidateId);

        Mockito.when(applicationUserService.fetchByUsername(applicationUser.getUsername())).thenReturn(applicationUser);
        Mockito.when(candidateService.fetch(candidateId)).thenReturn(existingCandidate);

        RunningVote expectedRunningVote = RunningVoteFactory.construct()
                .voterId(applicationUser.getId())
                .candidateId(candidateId)
                .candidateCategoryId(existingCandidate.getCandidateCategory().getId())
                .get();

        RunningVote savedRunningVote = voteService.vote(voteDto);

        assertEquals(expectedRunningVote, savedRunningVote);
    }

    @Test(expected = CandidateNotFoundException.class)
    public void vote_expectThrowException_whenCandidateIsInvalid() {
        ApplicationUser applicationUser = ApplicationUserFactory.construct().get();

        UUID candidateId = UUID.randomUUID();
        VoteDto voteDto = new VoteDto(candidateId);

        Mockito.when(applicationUserService.fetchByUsername(applicationUser.getUsername())).thenReturn(applicationUser);
        Mockito.when(candidateService.fetch(candidateId)).thenThrow(CandidateNotFoundException.class);

        voteService.vote(voteDto);
    }

    @Test(expected = IllegalVoterException.class)
    public void vote_expectThrowException_whenVoterAlreadyVotedOnSameElectionCategory() {
        Candidate existingCandidate = CandidateFactory.construct().get();
        RunningVote existingRunningVote = RunningVoteFactory.construct()
                .candidateCategoryId(existingCandidate.getCandidateCategory().getId())
                .get();
        ApplicationUser applicationUser = ApplicationUserFactory.construct().get();
        UUID candidateId = existingCandidate.getId();
        VoteDto voteDto = new VoteDto(candidateId);

        Mockito.when(applicationUserService.fetchByUsername(applicationUser.getUsername())).thenReturn(applicationUser);
        Mockito.when(candidateService.fetch(candidateId)).thenReturn(existingCandidate);
        Mockito.when(runningVoteRepository
                .findAllByCandidateCategoryIdAndVoterId(existingCandidate.getCandidateCategory().getId(), applicationUser.getId()))
                .thenReturn(Optional.of(Collections.singletonList(existingRunningVote)));

        voteService.vote(voteDto);
    }

}
