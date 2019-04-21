package com.personal.evote.lookup.appuser.service;

import com.personal.evote.factory.lookup.appuser.ApplicationUserFactory;
import com.personal.evote.factory.lookup.candidate.CandidateFactory;
import com.personal.evote.lookup.appuser.exception.UserNotFoundException;
import com.personal.evote.lookup.appuser.model.ApplicationUser;
import com.personal.evote.lookup.appuser.repository.ApplicationUserRepository;
import com.personal.evote.lookup.candidate.exception.CandidateNotFoundException;
import com.personal.evote.lookup.candidate.model.Candidate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationUserServiceTest {

    @InjectMocks
    private ApplicationUserService applicationUserService;

    @Mock
    private ApplicationUserRepository applicationUserRepository;

    @Test
    public void fetch_expectReturnApplicationUser_whenApplicationUserIsAvailable() {
        ApplicationUser expectedApplicationUser = ApplicationUserFactory.construct().get();

        Mockito.when(applicationUserRepository.findByUsername(expectedApplicationUser.getUsername())).thenReturn(Optional.of(expectedApplicationUser));

        ApplicationUser availableApplicationUser = applicationUserService.fetchByUsername(expectedApplicationUser.getUsername());

        assertEquals(expectedApplicationUser, availableApplicationUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void fetch_expectThrowException_whenUserIsNotAvailable() {
        String userToBeSearch = "wrong.username";
        Mockito.when(applicationUserRepository.findByUsername(userToBeSearch)).thenReturn(Optional.empty());

        applicationUserService.fetchByUsername(userToBeSearch);
    }

}
