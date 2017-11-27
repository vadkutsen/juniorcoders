package com.vadkutsen.juniorcoders.backend.service;

public abstract class AbstractEmailService implements EmailService {

	/**
	 * Creates a Simple Mail Message from a Feedback Pojo.
	 * @param feedback The feedback pojo
	 * @return
	 */
	protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedback) {
		return null; // for now
	}
}
