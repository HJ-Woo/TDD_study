package airplane.service;

import airplane.domain.*;
import airplane.dto.ScheduleRequest;
import airplane.dto.UserJoinDTO;
import airplane.repository.ReservationRespository;
import airplane.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @Mock
    private ReservationRespository reservationRepository;

    @Mock
    private ScheduleService scheduleService;

    @Mock
    private UserService userService;

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;
    private Schedule schedule;
    private User user;
    private String email = "testEmail";
    private String password = "testPassword";
    private int price = 1000;
    private Seat seat;

    @BeforeEach
    void setUp() {
        ScheduleRequest scheduleRequest = new ScheduleRequest("출발지", "도착지", LocalDate.of(2021, 3, 30), LocalDate.of(2021, 3, 31), 100);
        schedule = Schedule.createBuilder()
                .scheduleRequest(scheduleRequest)
                .build();

        UserJoinDTO joinDTO = new UserJoinDTO(1L, email, password);
        user = User.createBuilder()
                .userJoinDTO(joinDTO)
                .build();

        seat = Seat.defaultBuilder()
                .seatNumber(3)
                .classSeat(ClassSeat.FIRST)
                .status(SeatStatus.AVAILABLE)
                .build();

        Reservation tmp = Reservation.createBuilder().price(price).build();
        reservation = Reservation.defaultBuilder()
                .schedule(schedule)
                .user(user)
                .seat(seat)
                .reservation(tmp)
                .build();
    }

    @Test
    void 예약한다() {
        //given
        Long userId = 1L;
        Long scheduleId = 1L;
        Long seatId = 1L;
        given(scheduleService.findOne(any())).willReturn(schedule);
        given(userService.findOne(any())).willReturn(user);
        given(seatRepository.findById(any())).willReturn(Optional.of(seat));
        given(reservationRepository.save(any())).willReturn(reservation);

        //when
        Reservation saveReservation = reservationService.save(reservation, userId, scheduleId, seatId);

        //then
        assertThat(saveReservation.getUser()).isEqualTo(user);
        assertThat(saveReservation.getSeat()).isEqualTo(seat);
        assertThat(saveReservation.getSchedule()).isEqualTo(schedule);
    }

    @Test
    void 예약을취소한다() {
        //given
        Long reservationId = 1L;
        given(reservationRepository.findById(any())).willReturn(Optional.of(reservation));

        //when
        Reservation cancelReservation = reservationService.cancel(reservationId);

        //then
        assertThat(cancelReservation.getStatus()).isEqualTo(ReservationStatus.CANCELED);

    }

    @Test
    @Disabled
    void 예약상태를변경한다() {

    }


}
