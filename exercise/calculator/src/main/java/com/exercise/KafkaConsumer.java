package com.exercise;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class KafkaConsumer {
    private final CalculatorService calculatorService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaConsumer(CalculatorService calculatorService, KafkaTemplate<String, String> kafkaTemplate) {
        this.calculatorService = calculatorService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "calculator-requests", groupId = "calculator-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        String[] parts = message.split(",");
        String operation = parts[0];
        BigDecimal a = new BigDecimal(parts[1]);
        BigDecimal b = new BigDecimal(parts[2]);

        BigDecimal result = calculatorService.calculate(operation, a, b);

        String requestId = operation + "," + a + "," + b;
        kafkaTemplate.send("calculator-responses", requestId + ":" + result.toString());
    }
}
