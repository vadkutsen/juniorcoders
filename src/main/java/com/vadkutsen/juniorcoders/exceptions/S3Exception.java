package com.vadkutsen.juniorcoders.exceptions;

public class S3Exception extends RuntimeException {

    public S3Exception(Throwable e) {
        super(e);
    }
}
