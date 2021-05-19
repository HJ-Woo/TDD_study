package airplane.service;

import airplane.Message;
import airplane.domain.Reservation;
import airplane.domain.Schedule;
import airplane.domain.Seat;
import airplane.domain.User;
import airplane.repository.ReservationRespository;
import airplane.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRespository reservationRepository;
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final SeatRepository seatRepository;

    @Transactional
    public Reservation save(Reservation reservation, Long userId, Long scheduleId, Long seatId) {
        User user = userService.findOne(userId);
        Schedule schedule = scheduleService.findOne(scheduleId);
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new IllegalArgumentException(Message.EMPTY_SEAT));

        Reservation saveReservation = Reservation.defaultBuilder()
                .user(user)
                .schedule(schedule)
                .seat(seat)
                .reservation(reservation)
                .build();

        saveReservation.setUser(user);
        return reservationRepository.save(saveReservation);
    }

    @Transactional
    public Reservation cancel(Long reservationId) {
        Reservation reservation = findOne(reservationId);
        reservation.cancel();
        return reservation;
    }

    public Reservation findOne(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException(Message.EMPTY_RESERVATION));
    }
}
