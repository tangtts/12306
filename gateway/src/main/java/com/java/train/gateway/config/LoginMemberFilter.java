package com.java.train.gateway.config;

import com.java.train.gateway.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Tsk
 * @date 2024/5/15 0015
 */
@Component
public class LoginMemberFilter implements GlobalFilter, Ordered {
    static final  Logger LOG  = LoggerFactory.getLogger(LoginMemberFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        LOG.info("LoginMemberFilter filter path: " + path);

        if (path.contains("/admin")
                || path.contains("/hello")
                || path.contains("/member/member/login")
                || path.contains("/member/member/send-code")
        ) {
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        boolean validate = JwtUtil.validate(token);

        if (validate) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }


    /**
     * 优先级越小,执行越靠前
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}