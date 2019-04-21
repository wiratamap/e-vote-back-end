package com.personal.evote.authentication.service;

import com.personal.evote.factory.lookup.appuser.ApplicationUserFactory;
import com.personal.evote.lookup.appuser.model.ApplicationUser;
import com.personal.evote.lookup.appuser.repository.ApplicationUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private ApplicationUserRepository applicationUserRepository;

    @Test
    public void loadUserByUsername_expectReturnUserPrinciple_whenUsernameIsFound() {
        ApplicationUser existingApplicationUser = ApplicationUserFactory.construct().get();

        Mockito.when(applicationUserRepository.findByUsername(existingApplicationUser.getUsername())).thenReturn(Optional.of(existingApplicationUser));

        UserDetails userDetails = userDetailsService.loadUserByUsername(existingApplicationUser.getUsername());

        assertEquals(existingApplicationUser.getUsername(), userDetails.getUsername());
        assertEquals(existingApplicationUser.getPassword(), userDetails.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_expectThrowException_whenUsernameIsNotFound() {
        Mockito.when(applicationUserRepository.findByUsername("not.found.username")).thenReturn(Optional.empty());

        userDetailsService.loadUserByUsername("not.found.username");
    }

}
