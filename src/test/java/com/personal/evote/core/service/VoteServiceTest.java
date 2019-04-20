package com.personal.evote.core.service;

import com.personal.evote.core.model.RunningVote;
import com.personal.evote.core.model.dto.VoteDto;
import com.personal.evote.core.repository.RunningVoteRepository;
import com.personal.evote.factory.core.RunningVoteFactory;
import com.personal.evote.factory.lookup.candidate.CandidateFactory;
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

    @Before
    public void setUp() {
        Mockito.when(runningVoteRepository.save(any(RunningVote.class))).then(returnsFirstArg());
    }

    @Test
    public void vote_expectReturnSavedRunningVote_whenCandidateIsValid() {
        Candidate existingCandidate = CandidateFactory.construct().get();
        UUID voterId = UUID.randomUUID();
        UUID candidateId = existingCandidate.getId();
        VoteDto voteDto = new VoteDto(voterId, candidateId);

        Mockito.when(candidateService.fetch(candidateId)).thenReturn(existingCandidate);

        RunningVote expectedRunningVote = RunningVoteFactory.construct().voterId(voterId).candidateId(candidateId).get();

        RunningVote savedRunningVote = voteService.vote(voteDto);

        assertEquals(expectedRunningVote, savedRunningVote);
    }

    @Test(expected = CandidateNotFoundException.class)
    public void vote_expectThrowException_whenCandidateIsInvalid() {
        UUID voterId = UUID.randomUUID();
        UUID candidateId = UUID.randomUUID();
        VoteDto voteDto = new VoteDto(voterId, candidateId);

        Mockito.when(candidateService.fetch(candidateId)).thenThrow(CandidateNotFoundException.class);

        voteService.vote(voteDto);
    }

}
