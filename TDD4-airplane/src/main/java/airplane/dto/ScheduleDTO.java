package airplane.dto;

import airplane.domain.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ScheduleDTO {
    private Long id;
    private Long airlineId;
    private String arrivals;
    private String departures;
    private LocalDate arrivalTime;
    private LocalDate departureTime;
    private int capacity;

    public ScheduleDTO(Schedule schedule) {
        this.id = schedule.getId();
        this.airlineId = schedule.getAirline().getId();
        this.arrivals = schedule.getArrivals();
        this.departures = schedule.getDepartures();
        this.arrivalTime = schedule.getArrivalTime();
        this.departureTime = schedule.getDepartureTime();
        this.capacity = schedule.getAirplane().getCapacity();
    }
}
