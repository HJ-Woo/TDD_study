package airplane.dto;

import airplane.domain.Payment;
import airplane.domain.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long reservationId;
    private Long userId;
    private int amount; // 결제금액
    private int price; //예약금액
    private String account;
    private PaymentStatus status;
    private LocalDate date;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.reservationId = payment.getReservation().getId();
        this.userId = payment.getUser().getId();
        this.amount = payment.getAmount();
        this.price = payment.getReservation().getPrice();
        this.account = payment.getAccount();
        this.status = payment.getStatus();
        this.date = payment.getDate();
    }
}
