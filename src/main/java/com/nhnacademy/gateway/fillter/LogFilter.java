package com.nhnacademy.gateway.fillter;

import com.netflix.spectator.impl.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Config> {
    private final Environment environment;

    public LogFilter(Environment environment) {
        super(LogFilter.Config.class);
        this.environment = environment;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            log.info(request.getURI().toString());
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }

}
