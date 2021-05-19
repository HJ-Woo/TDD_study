package airplane.controller;


import airplane.domain.Airline;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirlineControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static HttpEntity<String> entity;
    private static String airlineName = "항공사";
    private static String saveUri = "/api/airline/new";
    private static String searchUri = "/api/airline";
    private static String searchAllUri = "/api/airlines";

    @BeforeAll
    static void setUp() {
        entity = new HttpEntity<>(airlineName);
    }

    @Test
    public void airline을저장한다() {
        ResponseEntity<Airline> result = testRestTemplate.postForEntity(saveUri, entity, Airline.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getName()).isEqualTo(airlineName);
    }

    @Test
    public void airline하나를_조회한다() {
        ResponseEntity<Airline> result = testRestTemplate.getForEntity(searchUri + "/{id}", Airline.class, 1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getName()).isEqualTo("항공사1");
    }

    @Test
    public void airline전체를_조회한다() {
        ResponseEntity<List> result = testRestTemplate.getForEntity(searchAllUri, List.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(result.getBody().size()).isEqualTo(5);
    }
}
