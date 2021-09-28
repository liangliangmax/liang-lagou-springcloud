package com.liang.lagou.gateway.filter;

import com.liang.lagou.code.CodeApi;
import com.liang.lagou.pojo.RestApiResult;
import com.liang.lagou.user.UserInfoApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.*;

@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {

    @Value("#{'${api.ignore}'.split(',')}")
    private List<String> ignorePath;

    @Autowired
    private UserInfoApi userInfoApi;


    ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        RequestPath path = exchange.getRequest().getPath();

        if(noNeedFilter(path.toString())){
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("token");

        if(StringUtils.isEmpty(token)){
            log.info( "token is empty..." );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }


        // WebFlux异步调用，同步会报错
        Future future = executorService.submit((Callable) () -> userInfoApi.getEmailByToken(token));

        try {
            Object object= future.get();
            RestApiResult<String> restApiResult = (RestApiResult<String>) object;

            if(StringUtils.isEmpty(restApiResult.getData())){
                log.info( "token is error..." );
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
