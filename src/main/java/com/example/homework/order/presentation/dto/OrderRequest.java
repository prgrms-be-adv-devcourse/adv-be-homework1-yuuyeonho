package com.example.homework.order.presentation.dto;

import com.example.homework.order.application.dto.OrderCommand;

import java.util.UUID;

public record OrderRequest(
        String productId,
        String memberId
) {

    public OrderCommand toCommand() {
        UUID product = productId != null ? UUID.fromString(productId) : null;
        UUID member = memberId != null ? UUID.fromString(memberId) : null;
        return new OrderCommand(product, member);
    }
}