package airplane.service;

import airplane.Message;
import airplane.domain.Payment;
import airplane.domain.Reservation;
import airplane.domain.User;
import airplane.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationService reservationService;
    private final UserService userService;

    @Transactional
    public Payment pay(Payment payment, Long reservationId, Long userId) {
        Reservation reservation = reservationService.findOne(reservationId);
        User user = userService.findOne(userId);

        Payment savePayment = Payment.defaultBuilder()
                .user(user)
                .reservation(reservation)
                .payment(payment)
                .build();

        user.accumulate(savePayment.getReservation().getPrice());
        return paymentRepository.save(savePayment);
    }

    @Transactional
    public Payment cancel(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException(Message.EMPTY_PAYMENT));

        payment.cancel();
        payment.getUser().cancelAccumulate(payment.getReservation().getPrice());

        return payment;
    }
}

