package com.personal.evote.factory.lookup.appuser;

import com.personal.evote.lookup.appuser.model.ApplicationUser;

import java.util.UUID;

public class ApplicationUserFactory {

    private static ApplicationUser applicationUser;

    public static ApplicationUserFactory construct() {
        applicationUser = ApplicationUser.builder()
                .username("john.doe")
                .name("John Doe")
                .email("johndoe@gmail.com")
                .password("secret")
                .build();
        applicationUser.setId(UUID.randomUUID());

        return new ApplicationUserFactory();
    }

    public ApplicationUser get() {
        return applicationUser;
    }
}
