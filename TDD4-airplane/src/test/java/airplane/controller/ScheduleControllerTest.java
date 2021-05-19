package airplane.controller;

import airplane.domain.Schedule;
import airplane.dto.ScheduleDTO;
import airplane.dto.ScheduleRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static HttpEntity<ScheduleRequest> entity;
    private static ScheduleRequest request;
    private static String saveUri = "/api/{airline_id}/schedule/new";
    private static String updateUri = "/api/{schedule_id}/update";
    private static String deleteUri = "/api/{schedule_id}/delete";
    private static String searchAllUri = "/api/{airline_id}/schedules";

    @BeforeAll
    static void setUp() {
        request = new ScheduleRequest("출발지", "도착지", LocalDate.of(2021, 3, 30), LocalDate.of(2021, 3, 31), 100);
        entity = new HttpEntity<>(request);
    }

    @Test
    @Order(1)
    void schedule을저장한다() {
        //given
        Map<String, Long> uriVariable = new HashMap<>();
        Long airlineId = 1L;
        uriVariable.put("airline_id", airlineId);

        //when
        ResponseEntity<ScheduleDTO> result = testRestTemplate.postForEntity(saveUri, entity, ScheduleDTO.class, uriVariable);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getArrivals()).isEqualTo(request.getArrivals());
        assertThat(result.getBody().getDepartures()).isEqualTo(request.getDepartures());
        assertThat(result.getBody().getArrivalTime()).isEqualTo(request.getArrivalTime());
        assertThat(result.getBody().getDepartureTime()).isEqualTo(request.getDepartureTime());

    }

    @Test
    @Order(2)
    void schedule을수정한다() {
        //given
        Map<String, Long> uriVariable = new HashMap<>();
        Long scheduleId = 1L;
        uriVariable.put("schedule_id", scheduleId);

        //when
        ResponseEntity<Schedule> result = testRestTemplate.postForEntity(updateUri, entity, Schedule.class, uriVariable);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getArrivals()).isEqualTo(request.getArrivals());
        assertThat(result.getBody().getDepartures()).isEqualTo(request.getDepartures());
        assertThat(result.getBody().getArrivalTime()).isEqualTo(request.getArrivalTime());
        assertThat(result.getBody().getDepartureTime()).isEqualTo(request.getDepartureTime());
    }

    @Test
    @Order(3)
    void schedule을삭제한다() {
        //given
        Map<String, Long> uriVariable = new HashMap<>();
        Long scheduleId = 100L;
        uriVariable.put("schedule_id", scheduleId);

        //when
//        testRestTemplate.delete(deleteUri, uriVariable);
        ResponseEntity<Void> result = testRestTemplate.exchange(deleteUri, HttpMethod.DELETE, null, Void.class, uriVariable);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(4)
    void schdule을항공사별조회한다() {
        //given
        Map<String, Long> uriVariable = new HashMap<>();
        Long airlineId = 1L;
        uriVariable.put("airline_id", airlineId);

        //when
        ResponseEntity<List> result = testRestTemplate.getForEntity(searchAllUri, List.class, uriVariable);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        result.getBody().forEach(schedule -> {
            assertThat(((LinkedHashMap)schedule).get("airlineId")).isEqualTo(new Long(airlineId).intValue());
        });
    }

}
