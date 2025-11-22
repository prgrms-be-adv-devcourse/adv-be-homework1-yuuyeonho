package com.example.homework.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    PurchaseOrder save(PurchaseOrder purchaseOrder);

    Page<PurchaseOrder> findAll(Pageable pageable);

    Optional<PurchaseOrder> findById(UUID id);
}
