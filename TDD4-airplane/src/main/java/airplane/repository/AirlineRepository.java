package airplane.repository;

import airplane.domain.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
