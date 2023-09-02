package com.ij34.redis.queue;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.ij34.model.HelloDto;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class DelayedMessageSender {

    @Autowired
    private RedissonClient redissonClient;



    public void sendDelayedMessage(HelloDto message, long delayMillis) {
        RBlockingQueue<HelloDto> blockingQueue = redissonClient.getBlockingQueue("my-delayedQueue");
        RDelayedQueue<HelloDto> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        delayedQueue.offer(message, delayMillis, TimeUnit.SECONDS);
        System.out.println(DateUtil.now()+"add::::"+message);
    }

    public Boolean remove(HelloDto record) {
        RBlockingQueue<HelloDto> blockingQueue = redissonClient.getBlockingQueue("my-delayedQueue");
        RDelayedQueue<HelloDto> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        return delayedQueue.remove(record);
    }

    public List<HelloDto> readAll() {
        RBlockingQueue<HelloDto> blockingQueue = redissonClient.getBlockingQueue("my-delayedQueue");
        RDelayedQueue<HelloDto> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        return delayedQueue.readAll();
    }
}
