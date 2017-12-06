package com.vadkutsen.juniorcoders.web.controllers;

import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.PasswordResetToken;
import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.User;
import com.vadkutsen.juniorcoders.backend.service.EmailService;
import com.vadkutsen.juniorcoders.backend.service.PasswordResetTokenService;
import com.vadkutsen.juniorcoders.backend.service.UserSecurityService;
import com.vadkutsen.juniorcoders.utils.UserUtils;
import com.vadkutsen.juniorcoders.backend.service.I18NService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForgotMyPassswordController {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    public static final String EMAIL_ADDRESS_VIEW_NAME = "forgotmypassword/emailForm";
    public static final String FORGOT_PASSWORD_URL_MAPPING = "/forgotmypassword";
    public static final String MAIL_SENT_KEY = "mailSent";
    public static final String CHANGE_PASSWORD_PATH = "/changeuserpassword";
    public static final String EMAIL_MESSAGE_PROPERTY_NAME = "forgotmypassword.email.text";

    @Autowired
    private I18NService i18NService;

    @Autowired
    private EmailService emailService;

    @Value("${webmaster.email}")
    private String webMasterEmail;


    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.GET)
    public String forgotPasswordGet() {
        return EMAIL_ADDRESS_VIEW_NAME;
    }

    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.POST)
    public String forgotPasswordPost(HttpServletRequest request, @RequestParam("email") String email, ModelMap model) {

        PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetTokenForEmail(email);

        if (null == passwordResetToken) {
            LOG.warn("Couldn't find a password reset token for email {}", email);
        } else {

            User user = passwordResetToken.getUser();
            String token = passwordResetToken.getToken();

            String resetPasswordUrl = UserUtils.createPasswordResetUrl(request, user.getId(), token);
            LOG.debug("ResetPassword URL {}", resetPasswordUrl);

            String emailText = i18NService.getMessage(EMAIL_MESSAGE_PROPERTY_NAME, request.getLocale());

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("[JuniorCoders]: How to Reset Your Password");
            mailMessage.setText(emailText + "\r\n" + resetPasswordUrl);
            mailMessage.setFrom(webMasterEmail);
        }

        model.addAttribute(MAIL_SENT_KEY, "true");

        return EMAIL_ADDRESS_VIEW_NAME;
    }
}