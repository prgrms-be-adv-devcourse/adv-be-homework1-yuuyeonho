package com.example.homework.order.infrastructure;

import com.example.homework.order.domain.OrderRepository;
import com.example.homework.order.domain.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder){
        return orderJpaRepository.save(purchaseOrder);
    }

    @Override
    public Page<PurchaseOrder> findAll(Pageable pageable){
        return orderJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<PurchaseOrder> findById(UUID id){
        return orderJpaRepository.findById(id);
    }
}
