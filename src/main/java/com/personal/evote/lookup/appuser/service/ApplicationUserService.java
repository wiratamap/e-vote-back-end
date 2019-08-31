package com.personal.evote.lookup.appuser.service;

import com.personal.evote.lookup.appuser.exception.UserNotFoundException;
import com.personal.evote.lookup.appuser.model.ApplicationUser;
import com.personal.evote.lookup.appuser.repository.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUser fetchByUsername(String username) {
        return applicationUserRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }
}
