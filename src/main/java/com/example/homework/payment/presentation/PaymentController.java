package com.example.homework.payment.presentation;

import com.example.homework.common.ResponseEntity;
import com.example.homework.payment.application.PaymentService;
import com.example.homework.payment.application.dto.PaymentFailureInfo;
import com.example.homework.payment.application.dto.PaymentInfo;
import com.example.homework.payment.presentation.dto.PaymentFailureRequest;
import com.example.homework.payment.presentation.dto.PaymentRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.v1}/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "결제 내역 조회", description = "확정된 결제 정보를 페이지 단위로 조회한다.")
    @GetMapping
    public ResponseEntity<List<PaymentInfo>> findAll(Pageable pageable){
        return paymentService.findAll(pageable);
    }

    @Operation(summary = "x=토스 결제 승인", description = "토스 결제 완료 후.")
    @PostMapping("/confirm")
    public ResponseEntity<PaymentInfo> confirm(@RequestBody PaymentRequest request){
        return paymentService.confirm(request.toCommand());
    }

    @PostMapping("/failure")
    public ResponseEntity<PaymentFailureInfo> failure(@RequestBody PaymentFailureRequest request){
        return paymentService.recordFailure(request.toCommand());
    }
}
