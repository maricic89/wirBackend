package com.milos.wir.security;

public class TokenNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1303405808304830421L;

    public TokenNotFoundException() {
        super();
    }

    public TokenNotFoundException(final String message) {
        super(message);
    }

    public TokenNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundException(final Throwable cause) {
        super(cause);
    }

    protected TokenNotFoundException(final String message, final Throwable cause, final boolean enableSuppression,
                                           final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}