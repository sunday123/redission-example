package com.ij34.redis.queue;

import cn.hutool.core.date.DateUtil;
import com.ij34.model.HelloDto;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DelayedMessageHandler {

    @Autowired
    private RedissonClient redissonClient;

    public void handleMessage(HelloDto message) {
        // 处理延迟消息的逻辑
        System.out.println(DateUtil.now()+"Handling delayed message: " + message);
    }

    public void startListening() {
        System.out.println("启动");
        RBlockingQueue<HelloDto> blockingQueue = redissonClient.getBlockingQueue("my-delayedQueue");
        RDelayedQueue<HelloDto> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        new Thread(() -> {
            try {
                while (true) {
                    HelloDto message = blockingQueue.take();
                    handleMessage(message);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
