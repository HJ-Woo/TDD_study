package airplane.controller;

import airplane.domain.Airline;
import airplane.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping("airline/new")
    public ResponseEntity<Airline> create(@RequestBody String name) {
        Airline airline = Airline.builder()
                .name(name)
                .build();

        Airline save = airlineService.save(airline);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(save);
    }

    @GetMapping("airline/{id}")
    public ResponseEntity<Airline> searchId(@PathVariable Long id) {
        return ResponseEntity.ok(airlineService.findOne(id));
    }

    @GetMapping("airlines")
    public ResponseEntity<List<Airline>> searchAll() {
        return ResponseEntity.ok(airlineService.findAll());
    }
}
