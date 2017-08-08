package com.innoq.matryoshka.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.util.Assert;

public class MatryoshkaSecurityFilter extends OncePerRequestFilter {

    private final String header;
    private final String expectedValue;

    public MatryoshkaSecurityFilter(final String header, final String expectedValue) {
        Assert.notNull(header, "header must not be null");
        Assert.notNull(expectedValue, "expectedValue must not be null");
        this.header = header;
        this.expectedValue = expectedValue;
    }

    public MatryoshkaSecurityFilter() {
        this(MatryoshkaSecurityRequest.X_FORWARDED_PROTO, MatryoshkaSecurityRequest.HTTPS);
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {

        final HttpServletRequest matryoshkaRequest =
                new MatryoshkaSecurityRequest(request, header, expectedValue);
        filterChain.doFilter(matryoshkaRequest, response);
    }
}
