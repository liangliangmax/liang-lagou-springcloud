package com.liang.lagou.gateway.filter;

import com.liang.lagou.code.CodeApi;
import com.liang.lagou.pojo.RestApiResult;
import com.liang.lagou.user.UserInfoApi;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.*;


@Component
@Slf4j
public class RegisterValidateCodeFilter implements GlobalFilter, Ordered {

    @Autowired
    private CodeApi codeApi;

    ExecutorService executorService = Executors.newFixedThreadPool(1);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();


        AntPathMatcher antPathMatcher  = new  AntPathMatcher();

        if(antPathMatcher.match("/**/register/**",path)){

            System.out.println("获取到注册接口");

            String leftPath = path.substring(path.indexOf("register/"));

            String[] split = leftPath.split("/");

            String email = split[1];

            System.out.println(email);

            String code = path.substring(path.lastIndexOf("/")+1);

            if(StringUtils.isEmpty(code)){

                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // WebFlux异步调用，同步会报错
            Future future = executorService.submit((Callable) () -> codeApi.validateCode(email,code));

            try {
                Object object= future.get();
                RestApiResult<Boolean> restApiResult = (RestApiResult<Boolean>) object;

                if(!restApiResult.getData()){
                    log.info( "token is error..." );
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -5;
    }


}
