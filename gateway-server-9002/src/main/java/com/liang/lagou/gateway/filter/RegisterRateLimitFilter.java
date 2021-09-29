package com.liang.lagou.gateway.filter;


import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class RegisterRateLimitFilter  implements GlobalFilter, Ordered {

    //注册请求的次数
    @Value("${ip.times}")
    private Integer registerTimes;
    //注册请求的时间间隔：秒
    @Value("${ip.seconds}")
    private Integer registerSeconds;

    //key为IP，value为注册的次数
    private Map<String, Integer> registerTimesMap = new ConcurrentHashMap<>();
    //key为IP，value为注册最近一次的时间
    private  Map<String, Long> registerSecondsMap = new ConcurrentHashMap<>();



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        AntPathMatcher antPathMatcher  = new  AntPathMatcher();

        if(antPathMatcher.match("/**/register/**",path)){

            //限流
            ServerHttpRequest request = exchange.getRequest();

            ServerHttpResponse response = exchange.getResponse();

            // 从request对象中获取客户端ip
            String clientIp = request.getRemoteAddress().getHostString();

            //判断注册次数
            if(registerTimesMap.containsKey(clientIp)) {
                Integer times = registerTimesMap.get(clientIp) + 1;

                if(times > registerTimes) {
                    response.setStatusCode(HttpStatus.FORBIDDEN); // 状态码
                    String data = "操作太频繁，请稍后再试";
                    DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
                    return response.writeWith(Mono.just(wrap));
                }
                registerTimesMap.put(clientIp, times);
            }else {
                registerTimesMap.put(clientIp, 1);
            }

            //判断注册时间
            Long now = System.currentTimeMillis();
            if(registerSecondsMap.containsKey(clientIp)) {
                Long lastTime = registerSecondsMap.get(clientIp);

                if((now - lastTime) < registerSeconds * 1000) {
                    response.setStatusCode(HttpStatus.FORBIDDEN); // 状态码
                    String data = "操作太频繁，请稍后再试";
                    DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
                    return response.writeWith(Mono.just(wrap));
                }
            }
            registerSecondsMap.put(clientIp, now);

        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -50;
    }
}
