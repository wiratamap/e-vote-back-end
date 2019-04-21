package com.personal.evote.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.evote.authentication.configuration.WebSecurityConfigurer;
import com.personal.evote.authentication.jwt.JwtAuthenticationEntryPoint;
import com.personal.evote.authentication.jwt.JwtProvider;
import com.personal.evote.authentication.service.UserDetailsServiceImpl;
import com.personal.evote.core.exception.IllegalVoterException;
import com.personal.evote.core.model.RunningVote;
import com.personal.evote.core.model.dto.VoteDto;
import com.personal.evote.core.service.VoteService;
import com.personal.evote.factory.core.RunningVoteFactory;
import com.personal.evote.lookup.appuser.repository.ApplicationUserRepository;
import com.personal.evote.lookup.candidate.exception.CandidateNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VoteController.class)
@Import({WebSecurityConfigurer.class, UserDetailsServiceImpl.class, JwtAuthenticationEntryPoint.class})
@MockBean({ApplicationUserRepository.class, JwtProvider.class})
@WithMockUser("john.doe")
public class VoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Value(value = "${request.mapping.path.vote}")
    private String voteRequestMappingPath;

    @Test
    public void vote_expectReturnCreated_whenCandidateIsValid() throws Exception {
        RunningVote runningVote = RunningVoteFactory.construct().get();
        VoteDto voteDto = new VoteDto(runningVote.getVoterId(), runningVote.getCandidateId());

        Mockito.when(voteService.vote(voteDto)).thenReturn(runningVote);

        mockMvc.perform(post(voteRequestMappingPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(voteDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void vote_expectReturnNotFound_whenCandidateIsInvalid() throws Exception {
        VoteDto voteDto = new VoteDto(UUID.randomUUID(), UUID.randomUUID());

        Mockito.when(voteService.vote(voteDto)).thenThrow(CandidateNotFoundException.class);

        mockMvc.perform(post(voteRequestMappingPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(voteDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void vote_expectReturnNotAcceptable_whenCandidateAlreadyVoteForRespectedCategory() throws Exception {
        RunningVote runningVote = RunningVoteFactory.construct().get();
        VoteDto voteDto = new VoteDto(runningVote.getVoterId(), runningVote.getCandidateId());

        Mockito.when(voteService.vote(voteDto)).thenThrow(IllegalVoterException.class);

        mockMvc.perform(post(voteRequestMappingPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(voteDto)))
                .andExpect(status().isNotAcceptable());
    }

}
