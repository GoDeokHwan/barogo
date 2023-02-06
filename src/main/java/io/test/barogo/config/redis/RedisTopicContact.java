package io.test.barogo.config.redis;

import org.springframework.data.redis.listener.ChannelTopic;

public class RedisTopicContact {
    static final String TOKEN = "TOKEN_V1";

    static final Integer ONE_HOUR_TIME = 60 * 60;

    public static ChannelTopic getTopic(String data) {
        return ChannelTopic.of(String.join(":", TOKEN, data));
    }

    public static int getOneHourTime() {
        return ONE_HOUR_TIME;
    }
}
