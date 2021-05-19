package airplane.service;


import airplane.domain.Payment;
import airplane.domain.PaymentStatus;
import airplane.domain.Reservation;
import airplane.domain.User;
import airplane.dto.UserJoinDTO;
import airplane.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private UserService userService;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;
    private Payment predict;
    private User user;
    private Reservation reservation;

    private static Long paymentId = 1L;
    private static Long reservationId = 1L;
    private static Long userId = 1L;

    @BeforeEach
    void setUp() {
        payment = Payment.createBuilder()
                .amount(10_000)
                .account("결제정보")
                .build();
        user = User.createBuilder()
                .userJoinDTO(new UserJoinDTO(1L, "email", "password"))
                .build();
        reservation = Reservation.createBuilder()
                .price(100_000)
                .build();
        predict = Payment.defaultBuilder()
                .user(user)
                .reservation(reservation)
                .payment(payment)
                .build();
    }

    @Test
    void 결제한다() {
        //given
        given(paymentRepository.save(any())).willReturn(predict);
        given(userService.findOne(any())).willReturn(user);
        given(reservationService.findOne(any())).willReturn(reservation);

        //when
        Payment savePayment = paymentService.pay(payment, reservationId, userId);

        //then
        assertThat(savePayment.getAmount()).isEqualTo(predict.getAmount());
        assertThat(savePayment.getAccount()).isEqualTo(predict.getAccount());
        assertThat(savePayment.getStatus()).isEqualTo(PaymentStatus.PAYED);
//        assertThat(savePayment.getUser().getId()).isEqualTo(userId);
        assertThat(savePayment.getUser().getMileage()).isEqualTo(user.getGrade().mileageCalc(reservation.getPrice()));

    }

    @Test
    void 결제취소한다() {
        //given
        given(paymentRepository.findById(any())).willReturn(Optional.of(predict));
        double mileage = user.getMileage();

        //when
        Payment updatePayment = paymentService.cancel(paymentId);

        //then
        assertThat(updatePayment.getAmount()).isEqualTo(payment.getAmount());
        assertThat(updatePayment.getAccount()).isEqualTo(payment.getAccount());
        assertThat(updatePayment.getStatus()).isEqualTo(PaymentStatus.CANCELD);
        assertThat(updatePayment.getUser().getMileage()).isEqualTo(mileage - user.getGrade().mileageCalc(reservation.getPrice()));

    }


}
