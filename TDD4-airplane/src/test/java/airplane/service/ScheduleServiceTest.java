package airplane.service;

import airplane.domain.Airline;
import airplane.domain.Schedule;
import airplane.dto.ScheduleRequest;
import airplane.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Mock
    private AirlineService airlineService;

    private static Schedule schedule;
    private static Airline airline;

    @BeforeAll
    static void setUp() {
        airline = Airline.builder()
                .name("항공사이름")
                .build();

        ScheduleRequest scheduleRequest = new ScheduleRequest("출발지", "도착지", LocalDate.of(2021, 3, 30), LocalDate.of(2021, 3, 31), 100);

        schedule = Schedule.createBuilder()
                .scheduleRequest(scheduleRequest)
                .build();
    }

    @Test
    void 스케줄_생성() {
        //given
        given(airlineService.findOne(any())).willReturn(airline);
        given(scheduleRepository.save(any())).willReturn(schedule);

        //when
        Schedule saveSchedule = scheduleService.save(schedule, 1L);

        //then
        assertThat(saveSchedule.getArrivals()).isEqualTo(saveSchedule.getArrivals());
    }

    @Test
    void 스케줄_수정() {
        //given
        given(scheduleRepository.findById(any())).willReturn(Optional.of(schedule));

        ScheduleRequest updateScheduleRequest = new ScheduleRequest(schedule.getArrivals(), schedule.getDepartures(), LocalDate.of(2021, 4, 29), LocalDate.of(2021, 4, 30), 100);
        Schedule updateSchedule = Schedule.createBuilder()
                .scheduleRequest(updateScheduleRequest)
                .build();

        //when
        Schedule result = scheduleService.update(1L, updateSchedule);

        //then
        assertThat(result.getArrivals()).isEqualTo(updateSchedule.getArrivals());
        assertThat(result.getDepartures()).isEqualTo(updateSchedule.getDepartures());
        assertThat(result.getArrivalTime()).isEqualTo(updateSchedule.getArrivalTime());
        assertThat(result.getDepartureTime()).isEqualTo(updateSchedule.getDepartureTime());
    }

    @Test
    void 스케줄_삭제() {
        //given
        Long scheduleId = 1L;

        //when
        scheduleService.delete(scheduleId);

        //then
        verify(scheduleRepository, times(1)).deleteById(scheduleId);
    }

    @Test
    void 스케줄_전체조회() {
        //given
        Long airlineId = 1L;
        given(scheduleRepository.findAllByAirline_Id(any())).willReturn(Collections.emptyList());

        //when
        List<Schedule> schedules = scheduleService.searchAirline(airlineId);

        //then
        verify(scheduleRepository, times(1)).findAllByAirline_Id(airlineId);
    }
}
