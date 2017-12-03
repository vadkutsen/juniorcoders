package com.vadkutsen.juniorcoders.utils;

import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.User;

public class UserUtils {

    /**
     * Non instantiable.
     */
    private UserUtils() {
        throw new AssertionError("Non instantiable");

    }

    /**
     * Creates a user with basic attributes set.
     * @param username  The username.
     * @param email The email.
     * @return A user entity.
     */
    public static User createBasicUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("secret");
        user.setEmail(email);
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
