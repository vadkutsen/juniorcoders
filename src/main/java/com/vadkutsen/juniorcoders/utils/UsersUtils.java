package com.vadkutsen.juniorcoders.utils;

import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.User;

public class UsersUtils {

    /**
     * Non instantiable.
     */
    private UsersUtils() {
        throw new AssertionError("Non instantiable");

    }

    /**
     * Creates a user with basic attributes set.
     */
    public static User createBasicUser() {
        User user = new User();
        user.setUsername("basicUser");
        user.setPassword("secret");
        user.setEmail("me@example.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhoneNumber("1234567890");
        user.setCountry("GB");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.inages.com/basicuser");
        return user;
    }
}
