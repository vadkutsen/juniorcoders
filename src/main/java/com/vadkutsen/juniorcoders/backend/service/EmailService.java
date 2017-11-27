package com.vadkutsen.juniorcoders.backend.service;

import com.vadkutsen.juniorcoders.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

/**
 * Contract for email service.
 * @author vadkutsen
 */

public interface EmailService {
	
	/**
	 * Sends an email with the content in the Feedback Pojo.
	 * @param feedbackPojo The feedback Pojo
	 */
	public void sendFeedbackEmail(FeedbackPojo feedbackPojo);
	
	public void sendGenericEmailMessage(SimpleMailMessage message);
	
	
}
