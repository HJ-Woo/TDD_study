package airplane.domain;

import airplane.dto.ScheduleRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleTest {

    private Airline airline;
    private Schedule schedule;
    private ScheduleRequest request;


    @BeforeEach
    void setUp() {
        airline = Airline.builder()
                .name("항공사이름")
                .build();

        request = new ScheduleRequest("출발지", "도착지", LocalDate.of(2021, 3, 30), LocalDate.of(2021, 3, 31), 100);
        schedule = Schedule.createBuilder()
                .scheduleRequest(request)
                .build();
    }

    @Test
    void 스케줄_변경감지() {
        //given
        ScheduleRequest updateRequest = new ScheduleRequest("출발지_변경", "도착지_변경", request.getArrivalTime(), LocalDate.of(2021, 4, 29), 100);
        Schedule updateSchedule = Schedule.createBuilder()
                .scheduleRequest(updateRequest)
                .build();

        //when
        schedule.update(updateSchedule);

        //then
        assertThat(schedule.getArrivals()).isEqualTo(updateSchedule.getArrivals());
        assertThat(schedule.getDepartures()).isEqualTo(updateSchedule.getDepartures());
        assertThat(schedule.getArrivalTime()).isEqualTo(updateSchedule.getArrivalTime());
        assertThat(schedule.getDepartureTime()).isEqualTo(updateSchedule.getDepartureTime());

    }
}
