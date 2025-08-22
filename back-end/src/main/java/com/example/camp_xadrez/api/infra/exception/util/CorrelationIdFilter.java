package com.example.camp_xadrez.api.infra.exception.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // garante que o MDC já esteja setado para filtros/handlers seguintes
public class CorrelationIdFilter extends OncePerRequestFilter {

    public static final String HEADER = "X-Correlation-Id";

    public static final String MDC_KEY = HEADER;

    private static final Pattern VALID_ID =
            Pattern.compile("^([a-fA-F0-9]{8}(-?[a-fA-F0-9]{4}){3}-?[a-fA-F0-9]{12}|[a-zA-Z0-9._-]{1,64})$");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String incoming = request.getHeader(HEADER);
        String correlationId = normalizeOrGenerate(incoming);


        MDC.put(MDC_KEY, correlationId);


        response.setHeader(HEADER, correlationId);

        try {
            filterChain.doFilter(request, response);
        } finally {

            MDC.remove(MDC_KEY);
        }
    }

    private String normalizeOrGenerate(String raw) {
        if (raw == null || raw.isBlank()) {
            return newCorrelationId();
        }
        String trimmed = raw.trim();
        if (VALID_ID.matcher(trimmed).matches()) {

            if (isUuid(trimmed)) {
                return toUuidCanonical(trimmed);
            }
            return trimmed;
        }

        return newCorrelationId();
    }

    private boolean isUuid(String s) {
        String noHyphen = s.replace("-", "");
        return noHyphen.length() == 32 && noHyphen.chars().allMatch(ch ->
                (ch >= '0' && ch <= '9') ||
                        (ch >= 'a' && ch <= 'f') ||
                        (ch >= 'A' && ch <= 'F'));
    }

    private String toUuidCanonical(String s) {
        String v = s.replace("-", "").toLowerCase();

        return v.substring(0, 8) + "-" +
                v.substring(8, 12) + "-" +
                v.substring(12, 16) + "-" +
                v.substring(16, 20) + "-" +
                v.substring(20);
    }

    private String newCorrelationId() {
        // UUID canônico (com hífens). Se preferir sem hífen, faça: UUID.randomUUID().toString().replace("-", "")
        return UUID.randomUUID().toString();
    }
}
