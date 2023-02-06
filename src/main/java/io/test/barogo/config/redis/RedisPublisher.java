package io.test.barogo.config.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisPublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, Object message, int time) {
        redisTemplate.opsForValue().set(topic.getTopic(), message, time, TimeUnit.SECONDS);
    }

    public boolean remove(ChannelTopic topic) {
        return redisTemplate.delete(topic.getTopic());
    }
}
