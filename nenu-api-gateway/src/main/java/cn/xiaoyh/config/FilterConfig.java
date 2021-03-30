package cn.xiaoyh.config;

import cn.xiaoyh.pojo.Result;
import cn.xiaoyh.utils.JWTUtils;
import cn.xiaoyh.utils.JsonUtils;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Configuration
public class FilterConfig {

    @Bean
    @Order(-1)
    public GlobalFilter authFilter() {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // 1. 从 header 中获取 token
            String token = request.getHeaders().getFirst("token");

            // 2. 验证 token
            if (StringUtils.isBlank(token)) {
                log.info("token 为空");
                return authError(response, "没有Token，请先登录");
            }

            try {
                Map<String, Claim> claimMap = JWTUtils.parseJwt(token);
                String userId = claimMap.get("userId").asString();
                request.mutate().headers(httpHeaders -> httpHeaders.add("userID", userId)).build();
            } catch (JWTVerificationException e) {
                return authError(response, "验证失败");
            }

            return chain.filter(exchange);
        });
    }

    private Mono<Void> authError(ServerHttpResponse resp, String msg) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        Result result = Result.error().setMsg(msg);

        return Optional.ofNullable(JsonUtils.serialize(result))
                .map(str -> str.getBytes(StandardCharsets.UTF_8))
                .map(bytes -> resp.bufferFactory().wrap(bytes))
                .map(buffer -> resp.writeWith(Mono.just(buffer)))
                .orElse(null);
    }
}
