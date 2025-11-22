package com.example.homework.order.application;

import com.example.homework.order.application.dto.OrderCommand;
import com.example.homework.order.application.dto.OrderInfo;
import com.example.homework.order.domain.OrderRepository;
import com.example.homework.order.domain.PurchaseOrder;
import com.example.homework.order.domain.PurchaseOrderStatus;
import com.example.homework.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    //TODO: setter 제거
    public ResponseEntity<OrderInfo> create(OrderCommand command) {
        PurchaseOrder order = PurchaseOrder.create(
                command.productId(),
                command.memberId()
        );
        PurchaseOrder saved = orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED.value(), OrderInfo.from(saved), 1);
    }

    public ResponseEntity<List<OrderInfo>> findAll(Pageable pageable) {
        Page<PurchaseOrder> purchaseOrderPage = orderRepository.findAll(pageable);
        List<OrderInfo> orderInfos = purchaseOrderPage.stream().map(OrderInfo::from).toList();
        return new ResponseEntity<>(HttpStatus.OK.value(), orderInfos, purchaseOrderPage.getTotalElements());
    }

    public ResponseEntity<OrderInfo> statusChange(String id, PurchaseOrderStatus status) {
        Optional<PurchaseOrder> optionalOrder = orderRepository.findById(UUID.fromString(id));
        if(optionalOrder.isPresent()){
            PurchaseOrder item = optionalOrder.get();
            item.statusChange(status);
            PurchaseOrder updated = orderRepository.save(item);
            return new ResponseEntity<>(HttpStatus.CREATED.value(), OrderInfo.from(updated), 1);
        }else{
            throw new IllegalArgumentException("order not found id :" + id);
        }
    }
}
