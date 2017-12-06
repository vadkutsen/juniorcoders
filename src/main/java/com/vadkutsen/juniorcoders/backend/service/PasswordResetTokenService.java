package com.vadkutsen.juniorcoders.backend.service;

import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.PasswordResetToken;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.User;
import com.vadkutsen.juniorcoders.backend.persistence.repositories.PasswordResetTokenRepository;
import com.vadkutsen.juniorcoders.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PasswordResetTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${token.expiration.length.minutes}")
    private int tokenExpirationInMinutes;

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);


    public PasswordResetToken createPasswordResetTokenForEmail(String email) {
        PasswordResetToken passwordResetToken = null;

        User user = userRepository.findByEmail(email);

        if (null != user) {
            String token = UUID.randomUUID().toString();
            LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
            passwordResetToken = new PasswordResetToken(token, user, now, tokenExpirationInMinutes);

            passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);
            LOG.debug("Successfully created token {} for user {}", token, user.getUsername());
        } else {
            LOG.warn("We couldn't find a user for the given email {}", email);
        }
        return passwordResetToken;
    }

    /**
     * Retrieves a Password reset token for the given token id.
     * @param token The token to be returned
     * @return a Password reset token for the given token id.
     */
    public PasswordResetToken findByToken (String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

}
