package com.vadkutsen.juniorcoders.backend.service;

import com.vadkutsen.juniorcoders.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.to.address}")
	private String defaultToAddress;

	/**
	 * Creates a Simple Mail Message from a Feedback Pojo.
	 * @param feedback The feedback pojo
	 * @return
	 */
	protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedback) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(defaultToAddress);
		message.setFrom(feedback.getEmail());
		message.setSubject("[Junior Coders]: Feedback received from " + feedback.getFirstName() + " " + feedback
				.getLastName() + "!");
		message.setText(feedback.getFeedback());
		return message;
	}

	@Override
	public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
		sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedbackPojo));
	}
}
