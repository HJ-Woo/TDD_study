package airplane.controller;

import airplane.domain.UserGrade;
import airplane.dto.UserDTO;
import airplane.dto.UserJoinDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static HttpEntity<UserJoinDTO> entity;
    private static UserJoinDTO joinDTO;
    private static String joinUri = "/api/user/new";
    private static String searchUri = "/api/user/{id}";
    private static Long airlineId = 1L;
    private static String email = "email";
    private static String password = "password";

    @BeforeAll
    static void setUp() {
        joinDTO = new UserJoinDTO(airlineId, email, password);
        entity = new HttpEntity<>(joinDTO);
    }

    @Test
    void 유저를생성한다() {
        ResponseEntity<UserDTO> result = testRestTemplate.postForEntity(joinUri, entity, UserDTO.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getEmail()).isEqualTo(email);
        assertThat(result.getBody().getMileage()).isEqualTo(0);
        assertThat(result.getBody().getAirlineId()).isEqualTo(airlineId);
        assertThat(result.getBody().getGrade()).isEqualTo(UserGrade.GOLD);
    }


    @Test
    void 유저를조회한다() {
        ResponseEntity<UserDTO> result = testRestTemplate.getForEntity(searchUri, UserDTO.class, 1L);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getEmail()).isEqualTo(email);
        assertThat(result.getBody().getMileage()).isEqualTo(0);
        assertThat(result.getBody().getAirlineId()).isEqualTo(airlineId);
        assertThat(result.getBody().getGrade()).isEqualTo(UserGrade.GOLD);
    }
}
