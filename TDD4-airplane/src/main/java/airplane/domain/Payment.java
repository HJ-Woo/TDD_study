package airplane.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private int amount; // 결제금액

    @Column(nullable = false)
    private String account; // 결제 수단

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PAYED;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate date = LocalDate.now(); // 결제일자


    public void cancel() {
        this.status = PaymentStatus.CANCELD;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName ="createBuilder")
    public Payment(int amount, String account) {
        this.amount = amount;
        this.account = account;
    }

    @Builder(builderClassName = "defaultBuilder", builderMethodName ="defaultBuilder")
    public Payment(Reservation reservation, User user, Payment payment) {
        this.reservation = reservation;
        this.user = user;
        this.amount = payment.getAmount();
        this.account = payment.getAccount();
    }
}
