package com.vadkutsen.juniorcoders.exceptions;

import com.stripe.exception.AuthenticationException;

public class StripeException extends RuntimeException {
    public StripeException(Throwable e) {
        super(e);
    }
}
