package airplane.controller;


import airplane.domain.PaymentStatus;
import airplane.dto.PaymentDTO;
import airplane.dto.PaymentRequest;
import airplane.dto.UserDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private static HttpEntity<PaymentRequest> entity;
    private static String saveUri = "/api/payment/pay";
    private static String cancelUri = "/api/payment/{payment_id}/cancel";
    private static String userSearchUri = "/api/user/{id}";

    private static PaymentRequest request;
    private static ResponseEntity<PaymentDTO> result;
    private static double mileage;

    @BeforeAll
    static void setUp() {
        request = new PaymentRequest(1L, 1L, 100_000, "결제정보");
        entity = new HttpEntity<>(request);
    }

    @Test
    @Order(1)
    void 결제한다() {
        //when
        result = testRestTemplate.postForEntity(saveUri, entity, PaymentDTO.class);
        ResponseEntity<UserDTO> userResult = testRestTemplate.getForEntity(userSearchUri, UserDTO.class, result.getBody().getUserId());


        //then
        mileage = userResult.getBody().getMileage();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getReservationId()).isEqualTo(request.getReservationId());
        assertThat(result.getBody().getUserId()).isEqualTo(request.getUserId());
        assertThat(result.getBody().getAmount()).isEqualTo(request.getAmount());
        assertThat(result.getBody().getAccount()).isEqualTo(request.getAccount());
        assertThat(result.getBody().getStatus()).isEqualTo(PaymentStatus.PAYED);
        assertThat(mileage).isEqualTo(userResult.getBody().getGrade().mileageCalc(result.getBody().getPrice()));

    }

    @Test
    @Order(2)
    void 결제취소한다() {
        //when
        ResponseEntity<PaymentDTO> cancelResult = testRestTemplate.postForEntity(cancelUri, entity, PaymentDTO.class, result.getBody().getId());
        ResponseEntity<UserDTO> userResult = testRestTemplate.getForEntity(userSearchUri, UserDTO.class, result.getBody().getUserId());

        //then
        assertThat(cancelResult.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(cancelResult.getBody().getStatus()).isEqualTo(PaymentStatus.CANCELD);
        assertThat(userResult.getBody().getMileage()).isEqualTo(mileage - userResult.getBody().getGrade().mileageCalc(result.getBody().getPrice()));
    }
}
