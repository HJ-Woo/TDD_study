package airplane.controller;

import airplane.domain.Reservation;
import airplane.dto.ReservationDTO;
import airplane.dto.ReservationRequest;
import airplane.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("reservation/new")
    public ResponseEntity<ReservationDTO> create(@RequestBody ReservationRequest request) {
        Reservation reservation = Reservation.createBuilder()
                .price(request.getPrice())
                .build();

        Reservation saveReservation = reservationService.save(reservation, request.getUserId(), request.getScheduleId(), request.getSeatId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ReservationDTO(saveReservation));
    }

    @PostMapping("{reservation_id}/cancel")
    public ResponseEntity<ReservationDTO> cancel(@PathVariable("reservation_id") Long reservationId) {
        Reservation cancelReservation = reservationService.cancel(reservationId);
        return ResponseEntity.ok(new ReservationDTO(cancelReservation));
    }
}
