package airplane.service;

import airplane.Message;
import airplane.domain.Airline;
import airplane.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AirlineService {

    private final AirlineRepository airlineRepository;

    @Transactional
    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline findOne(Long airlineId) {
        return airlineRepository.findById(airlineId)
                .orElseThrow(() -> new IllegalArgumentException(Message.EMPTY_SEAT));
    }

    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }
}
