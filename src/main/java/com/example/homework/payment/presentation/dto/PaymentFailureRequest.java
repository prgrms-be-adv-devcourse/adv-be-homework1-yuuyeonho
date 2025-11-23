package com.example.homework.payment.presentation.dto;

import com.example.homework.payment.application.dto.PaymentFailCommand;

public record PaymentFailureRequest(
        String orderId,
        String paymentKey,
        String errorCode,
        String errorMessage,
        Long amount,
        String rawPayload
) {
    public PaymentFailCommand toCommand(){
        return new PaymentFailCommand(orderId, paymentKey, errorCode, errorMessage, amount, rawPayload);
    }
}
