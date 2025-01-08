package com.exercise;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ConcurrentHashMap<String, CompletableFuture<String>> responseMap = new ConcurrentHashMap<>();

    public CalculatorController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/{operation}")
    public CompletableFuture<String> calculate(@PathVariable String operation,
                                               @RequestParam String a,
                                               @RequestParam String b) {
        String requestId = operation + "," + a + "," + b;
        CompletableFuture<String> future = new CompletableFuture<>();
        responseMap.put(requestId, future);
        kafkaTemplate.send("calculator-requests", requestId);
        return future;
    }

    @KafkaListener(topics = "calculator-responses", groupId = "rest-group")
    public void consume(String message) {
        String[] parts = message.split(":");
        String requestId = parts[0];
        String result = parts[1];
        if (responseMap.containsKey(requestId)) {
            responseMap.get(requestId).complete(result);
            responseMap.remove(requestId);
        }
    }
}
