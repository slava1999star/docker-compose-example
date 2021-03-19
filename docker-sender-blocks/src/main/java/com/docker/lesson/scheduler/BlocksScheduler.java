package com.docker.lesson.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Slf4j
@Component
public class BlocksScheduler {

    private RestOperations restOperations;
    private RabbitOperations rabbitOperations;

    @Autowired
    public BlocksScheduler(RestOperations restOperations, RabbitOperations rabbitOperations) {
        this.restOperations = restOperations;
        this.rabbitOperations = rabbitOperations;
    }

    @Scheduled(initialDelay = 10000, fixedRateString = "${blocks.schedule}")
    private void doWork() {
        ResponseEntity<String> lastBlock = restOperations.getForEntity(
                "http://api.blockcypher.com/v1/beth/test",
                String.class
        );

        rabbitOperations.send(
                MessageBuilder
                        .withBody(lastBlock.getBody().getBytes())
                        .build()
        );
    }
}
