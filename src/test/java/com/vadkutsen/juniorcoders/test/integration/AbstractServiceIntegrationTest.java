package com.vadkutsen.juniorcoders.test.integration;

import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.Role;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.User;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.UserRole;
import com.vadkutsen.juniorcoders.backend.service.UserService;
import com.vadkutsen.juniorcoders.enums.PlansEnum;
import com.vadkutsen.juniorcoders.enums.RolesEnum;
import com.vadkutsen.juniorcoders.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractServiceIntegrationTest {
    @Autowired
    protected UserService userService;

    protected User createUser(TestName testName) {
        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@juniorcoders.com";

        Set<UserRole> userRoles = new HashSet<>();
        User basicUser = UserUtils.createBasicUser(username, email);
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        return userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
    }
}
