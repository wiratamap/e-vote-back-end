package com.personal.evote.core.service;

import com.personal.evote.core.model.RunningVote;
import com.personal.evote.core.model.dto.VoteDto;
import com.personal.evote.core.repository.RunningVoteRepository;
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

    @Before
    public void setUp() {
        Mockito.when(runningVoteRepository.save(any(RunningVote.class))).then(returnsFirstArg());
    }

    @Test
    public void vote_expectReturnSavedRunningVote_whenCandidateIsValid() {
        UUID voterId = UUID.randomUUID();
        UUID candidateId = UUID.randomUUID();
        VoteDto voteDto = new VoteDto(voterId, candidateId);

        RunningVote expectedRunningVote = RunningVote.builder()
                .voterId(voterId)
                .candidateId(candidateId)
                .build();

        RunningVote savedRunningVote = voteService.vote(voteDto);

        assertEquals(expectedRunningVote, savedRunningVote);
    }

}
