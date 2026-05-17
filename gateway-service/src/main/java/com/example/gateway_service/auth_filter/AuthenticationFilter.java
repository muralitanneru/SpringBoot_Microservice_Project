package com.example.gateway_service.auth_filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.example.gateway_service.util.JwtUtil;

@Component
public class AuthenticationFilter
        extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {

        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {

            if (routeValidator.isSecured
                    .test(exchange.getRequest())) {

                if (!exchange.getRequest()
                        .getHeaders()
                        .containsKey(HttpHeaders.AUTHORIZATION)) {

                    throw new RuntimeException(
                            "Missing Authorization Header");
                }

                String authHeader =
                        exchange.getRequest()
                                .getHeaders()
                                .get(HttpHeaders.AUTHORIZATION)
                                .get(0);

                String token = null;

                if (authHeader != null
                        && authHeader.startsWith("Bearer ")) {

                    token = authHeader.substring(7);
                }

                try {

                    jwtUtil.validateToken(token);

                } catch (Exception e) {

                    throw new RuntimeException(
                            "Unauthorized Access");
                }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}