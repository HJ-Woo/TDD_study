package airplane.dto;

import airplane.domain.ClassSeat;
import airplane.domain.Reservation;
import airplane.domain.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private ScheduleDTO scheduleDTO;
    private Long userId;
    private int seatNumber;
    private ClassSeat classSeat;
    private int price;
    private ReservationStatus status;

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.scheduleDTO = new ScheduleDTO(reservation.getSchedule());
        this.userId = reservation.getUser().getId();
        this.seatNumber = reservation.getSeat().getSeatNumber();
        this.classSeat = reservation.getSeat().getClassSeat();
        this.price = reservation.getPrice();
        this.status = reservation.getStatus();
    }
}
