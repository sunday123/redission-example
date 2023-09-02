package com.ij34.redis.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DelayedMessageListener implements ApplicationRunner {

    @Autowired
    private DelayedMessageHandler messageHandler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageHandler.startListening();
    }
}
