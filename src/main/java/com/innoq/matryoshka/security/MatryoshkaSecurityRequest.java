package com.innoq.matryoshka.security;

import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MatryoshkaSecurityRequest extends HttpServletRequestWrapper {

    public static final String X_FORWARDED_PROTO = "x-forwarded-proto";
    public static final String HTTPS = "https";

    private final String header;
    private final String expectedValue;

    public MatryoshkaSecurityRequest(final HttpServletRequest request,
                                     final String header,
                                     final String expectedValue) {
        super(request);
        Assert.notNull(request, "request must not be null");
        Assert.notNull(header, "header must not be null");
        Assert.notNull(expectedValue, "expectedCalue must not be null");
        this.header = header;
        this.expectedValue = expectedValue;
    }

    public MatryoshkaSecurityRequest(final HttpServletRequest request) {
        this(request, X_FORWARDED_PROTO, HTTPS);
    }

    @Override
    public final boolean isSecure() {
        final String headerValue = getHeader(header);
        return super.isSecure() || (headerValue != null && headerValue.equals(expectedValue));
    }
}
