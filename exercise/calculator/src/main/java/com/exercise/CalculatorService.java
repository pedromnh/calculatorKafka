package com.exercise;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {
    public BigDecimal calculate(String operation, BigDecimal a, BigDecimal b) {
        return switch (operation) {
            case "sum" -> a.add(b);
            case "subtraction" -> a.subtract(b);
            case "multiplication" -> a.multiply(b);
            case "division" -> a.divide(b);
            default -> throw new IllegalArgumentException("Invalid operation");
        };
    }

}