package com.vadkutsen.juniorcoders.test.integeration;


import com.vadkutsen.juniorcoders.JuniorcodersApplication;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.Plan;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.Role;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.User;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.UserRole;
import com.vadkutsen.juniorcoders.backend.persistence.repositories.PlanRepository;
import com.vadkutsen.juniorcoders.backend.persistence.repositories.RoleRepository;
import com.vadkutsen.juniorcoders.backend.persistence.repositories.UserRepository;
import com.vadkutsen.juniorcoders.enums.PlansEnum;
import com.vadkutsen.juniorcoders.enums.RolesEnum;
import com.vadkutsen.juniorcoders.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JuniorcodersApplication.class)
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {


    private static final int BASIC_PLAN_ID = 1;

    @Rule public TestName testName = new TestName();

    @Before
    public void init() {
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
    }


    @Test
    public void testCreateNewPlan() throws Exception {
        Plan basicPLan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPLan);
        Plan retrievedPlan = planRepository.findOne(PlansEnum.BASIC.getId());
        Assert.assertNotNull(retrievedPlan);
    }

    @Test
    public void testCreateNewRole() throws Exception {
        Role userRole = createRole(RolesEnum.BASIC);
        roleRepository.save(userRole);
        Role retrievedRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrievedRole);
    }

    @Test
    public void testCreateNewUser() throws Exception {

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@juniorcoders.com";

        User basicUser = createUser(username, email);

        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set<UserRole> newlyCreatedUserUserRoles = newlyCreatedUser.getUserRoles();

        for (UserRole ur : newlyCreatedUserUserRoles) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }

    @Test
    public void testDeleteUser() throws Exception {

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@juniorcoders.com";

        User basicUser = createUser(username, email);
        userRepository.delete(basicUser.getId());
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        User user = createUser(testName);

        User newlyFoundUser = userRepository.findByEmail(user.getEmail());
        Assert.assertNotNull(newlyFoundUser);
        Assert.assertNotNull(newlyFoundUser.getId());
    }

    @Test
    public void testUpdateUserPassword() throws Exception {
        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        String newPassword = UUID.randomUUID().toString();

        userRepository.updateUserPassword(user.getId(), newPassword);

        user = userRepository.findOne(user.getId());
        Assert.assertEquals(newPassword, user.getPassword());
    }

}
