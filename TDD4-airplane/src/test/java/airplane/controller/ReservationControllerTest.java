package airplane.controller;

import airplane.dto.ReservationDTO;
import airplane.dto.ReservationRequest;
import airplane.domain.ReservationStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static HttpEntity<ReservationRequest> entity;
    private static ReservationRequest requestDTO;
    private static String saveUri = "/api/reservation/new";
    private static String deleteUri = "/api/{reservation_id}/cancel";
    private static Long seatId = 1L;
    private static Long scheduleId = 1L;
    private static Long userId = 1L;
    private static int price = 1000;

    @BeforeAll
    static void setUp() {
        requestDTO = new ReservationRequest(scheduleId, userId, seatId, price);
        entity = new HttpEntity<>(requestDTO);
    }

    @Test
    @Order(1)
    void 예약한다() {
        //when
        ResponseEntity<ReservationDTO> result = testRestTemplate.postForEntity(saveUri, entity, ReservationDTO.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getUserId()).isEqualTo(userId);
        assertThat(result.getBody().getScheduleDTO().getId()).isEqualTo(scheduleId);
        assertThat(result.getBody().getPrice()).isEqualTo(price);
        assertThat(result.getBody().getStatus()).isEqualTo(ReservationStatus.RESERVED);
    }

    @Test
    @Order(2)
    void 예약을취소한다() {
        //given
        Map<String, Long> uriVariable = new HashMap<>();
        Long reservationId = 1L;
        uriVariable.put("reservation_id", reservationId);


        //when
        ResponseEntity<ReservationDTO> result = testRestTemplate.postForEntity(deleteUri, entity, ReservationDTO.class, uriVariable);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getStatus()).isEqualTo(ReservationStatus.CANCELED);
    }
}
