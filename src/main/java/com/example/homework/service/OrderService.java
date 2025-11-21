package com.example.homework.service;

import com.example.homework.entity.PurchaseOrder;
import com.example.homework.entity.PurchaseOrderStatus;
import com.example.homework.entity.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.homework.repository.OrderJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    public ResponseEntity<PurchaseOrder> create(PurchaseOrder request) {
        request.setSellerId(UUID.randomUUID());
        request.setMemberId(UUID.randomUUID());
        request.setProductId(UUID.randomUUID());
        PurchaseOrder order = orderJpaRepository.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED.value(), order, 1);
    }

    public ResponseEntity<List<PurchaseOrder>> findAll(Pageable pageable) {
        Page<PurchaseOrder> purchaseOrderPage = orderJpaRepository.findAll(pageable);
        return new ResponseEntity<>(HttpStatus.OK.value(), purchaseOrderPage.stream().toList(), purchaseOrderPage.getTotalElements());
    }

    public ResponseEntity<PurchaseOrder> statusChange(String id, PurchaseOrderStatus status) {
        Optional<PurchaseOrder> optionalOrder = orderJpaRepository.findById(UUID.fromString(id));
        if(optionalOrder.isPresent()){
            PurchaseOrder item = optionalOrder.get();
            item.setStatus(status);
            return new ResponseEntity<>(HttpStatus.CREATED.value(), orderJpaRepository.save(item), 1);
        }else{
            throw new IllegalArgumentException("order not found id :" + id);
        }
    }
}
