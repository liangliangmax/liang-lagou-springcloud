package com.liang.lagou.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {

    @Value("#{'${api.ignore}'.split(',')}")
    private List<String> ignorePath;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        RequestPath path = exchange.getRequest().getPath();

        if(noNeedFilter(path.toString())){
            return chain.filter(exchange);
        }

        HttpCookie cookie = exchange.getRequest().getCookies().getFirst("token");

        if(cookie == null || StringUtils.isEmpty(cookie.getValue())){
            log.info( "token is empty..." );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }


    private boolean noNeedFilter(String path){


        for (String ignore : ignorePath) {

            AntPathMatcher antPathMatcher = new AntPathMatcher();

            if(antPathMatcher.match(ignore,path))
                return true;

        }

        return false;

    }
}
