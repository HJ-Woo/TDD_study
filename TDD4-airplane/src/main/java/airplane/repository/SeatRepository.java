package airplane.repository;

import airplane.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
