package com.example.homework.payment.application.dto;

public record PaymentFailCommand(
        String orderId,
        String paymentKey,
        String errorCode,
        String errorMessage,
        Long amount,
        String rawPayload
) {
}