package com.docker.listener;

import com.docker.dto.BlockDto;
import com.docker.jpa.BlockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(bindings = {
        @QueueBinding(
                value = @Queue(
                        value = "blockListener",
                        autoDelete = "true"
                ),
                exchange = @Exchange(
                        value = "e.b.forward",
                        type = ExchangeTypes.TOPIC
                ),
                key = {
                        "r.last-block"
                }
        )
})
public class BlockListener {

    private final RedisOperations redisTemplate;
    private final BlockRepository blockRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public BlockListener(RedisOperations redisTemplate, BlockRepository blockRepository, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.blockRepository = blockRepository;
        this.objectMapper = objectMapper;
    }


    @RabbitHandler
    protected void receiveMessage(byte[] bytes) throws JsonProcessingException {
        redisTemplate.opsForValue().increment("received-block-count");
        blockRepository.save(objectMapper.readValue(new String(bytes), BlockDto.class));
    }
}
