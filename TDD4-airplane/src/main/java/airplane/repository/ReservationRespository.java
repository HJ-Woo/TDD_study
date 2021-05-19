package airplane.repository;

import airplane.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRespository extends JpaRepository<Reservation, Long> {
}
