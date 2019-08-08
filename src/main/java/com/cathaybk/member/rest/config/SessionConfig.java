package com.cathaybk.member.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * redis session 設定
 * @author NT81652
 *
 */
@SuppressWarnings("rawtypes")
@EnableRedisHttpSession
public class SessionConfig implements ApplicationListener {

    @Value("${spring.session.redis.timeout}")
    private Integer maxInactiveIntervalInSeconds;

    @Autowired
    private RedisOperationsSessionRepository redisOperation;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            redisOperation.setDefaultMaxInactiveInterval(maxInactiveIntervalInSeconds);
        }
    }
}

