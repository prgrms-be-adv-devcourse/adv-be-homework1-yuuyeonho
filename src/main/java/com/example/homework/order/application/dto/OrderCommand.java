package com.example.homework.order.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCommand(
        UUID productId,
        UUID memberId
) {
}
