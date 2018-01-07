package com.vadkutsen.juniorcoders.backend.service;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StripeService {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(StripeService.class);

    @Autowired
    private String stripeKey;

    public String createCustomer(Map<String, Object> tokenParams, Map<String, Object> customerParams) {

        Stripe.apiKey = stripeKey;
        String stripeCustomerId = null;
        try {
            Token token = Token.create(tokenParams);
            customerParams.put("source", token.getId());
            Customer customer = Customer.create(customerParams);
            stripeCustomerId = customer.getId();
        } catch(AuthenticationException e) {
            LOG.error("An Authentiction exception occurred while creating the Stripe customer", e);
            throw new com.vadkutsen.juniorcoders.exceptions.StripeException();
        } catch(InvalidRequestException e) {
            LOG.error("An Invald Request exception occurred while creating the Stripe customer", e);
            throw new com.vadkutsen.juniorcoders.exceptions.StripeException();
        } catch(APIConnectionException e) {
            LOG.error("An API Connection exception occurred while creating the Stripe customer", e);
            throw new com.vadkutsen.juniorcoders.exceptions.StripeException();
        } catch(CardException e) {
            LOG.error("A Card exception occurred while creating the Stripe customer", e);
            throw new com.vadkutsen.juniorcoders.exceptions.StripeException();
        } catch(APIException e) {
            LOG.error("An API exception occurred while creating the Stripe customer", e);
            throw new com.vadkutsen.juniorcoders.exceptions.StripeException();
        }
        return stripeCustomerId;
    }
}
