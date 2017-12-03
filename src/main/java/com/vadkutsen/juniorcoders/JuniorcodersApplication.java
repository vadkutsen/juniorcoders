package com.vadkutsen.juniorcoders;

import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.Role;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.User;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.UserRole;
import com.vadkutsen.juniorcoders.backend.service.UserService;
import com.vadkutsen.juniorcoders.enums.PlansEnum;
import com.vadkutsen.juniorcoders.enums.RolesEnum;
import com.vadkutsen.juniorcoders.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JuniorcodersApplication implements CommandLineRunner {

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(JuniorcodersApplication.class);

	@Autowired
	private UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(JuniorcodersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String username = "proUser";
		String email = "prouser@example.com";

		User user = UserUtils.createBasicUser(username, email);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RolesEnum.PRO)));
		LOG.debug("Creating user with username {}", user.getUsername());
		userService.createUser(user, PlansEnum.PRO, userRoles);
		LOG.info("User {} created", user.getUsername());

	}
}
