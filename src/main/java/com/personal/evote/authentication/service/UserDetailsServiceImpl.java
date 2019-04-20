package com.personal.evote.authentication.service;

import com.personal.evote.authentication.model.UserPrinciple;
import com.personal.evote.lookup.appuser.model.ApplicationUser;
import com.personal.evote.lookup.appuser.repository.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> username or email: " + username));

        return UserPrinciple.build(applicationUser);
    }
}
