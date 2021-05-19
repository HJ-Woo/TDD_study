package airplane.controller;

import airplane.domain.Payment;
import airplane.dto.PaymentDTO;
import airplane.dto.PaymentRequest;
import airplane.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment/pay")
    public ResponseEntity<PaymentDTO> pay(@RequestBody PaymentRequest request) {
        Payment payment = Payment.createBuilder()
                .amount(request.getAmount())
                .account(request.getAccount())
                .build();

        Payment payed = paymentService.pay(payment, request.getReservationId(), request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PaymentDTO(payed));
    }

    @PostMapping("/payment/{payment_id}/cancel")
    public ResponseEntity<PaymentDTO> cancel(@PathVariable("payment_id") Long paymentId) {
        Payment canceledPayment = paymentService.cancel(paymentId);
        return ResponseEntity.ok(new PaymentDTO(canceledPayment));
    }

}
