package airplane.domain;

import airplane.dto.ScheduleRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airline airline;

    @Embedded
    private Airplane airplane;

    @Column(nullable = false)
    private String arrivals;

    @Column(nullable = false)
    private String departures;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate arrivalTime;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate departureTime;

    public void update(Schedule updateSchedule) {
        this.arrivals = updateSchedule.arrivals;
        this.departures = updateSchedule.departures;
        this.arrivalTime = updateSchedule.arrivalTime;
        this.departureTime = updateSchedule.departureTime;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Schedule(ScheduleRequest scheduleRequest) {
        this.arrivals = scheduleRequest.getArrivals();
        this.departures = scheduleRequest.getDepartures();
        this.arrivalTime = scheduleRequest.getArrivalTime();
        this.departureTime = scheduleRequest.getDepartureTime();
        this.airplane = new Airplane(scheduleRequest.getCapacity());
    }

    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public Schedule(Airline airline, Schedule schedule) {
        this.airline = airline;
        this.arrivals = schedule.arrivals;
        this.departures = schedule.departures;
        this.arrivalTime = schedule.arrivalTime;
        this.departureTime = schedule.departureTime;
        this.airplane = new Airplane(schedule.getAirplane().getCapacity());
    }


}
