package airplane.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {
    private String arrivals;
    private String departures;
    private LocalDate arrivalTime;
    private LocalDate departureTime;
    private int capacity;
}
