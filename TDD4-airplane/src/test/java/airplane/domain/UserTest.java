package airplane.domain;

import airplane.dto.UserJoinDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        UserJoinDTO dto = new UserJoinDTO(1L, "email", "pwd");
        user = User.createBuilder()
                .userJoinDTO(dto)
                .build();
    }

    @Test
    void 적립한다(){
        //given
        assertThat(user.getGrade()).isEqualTo(UserGrade.GOLD);

        //when
        user.accumulate(5_000_000);

        //then
        assertThat(user.getGrade()).isEqualTo(UserGrade.DIAMOND);
        assertThat(user.getMileage()).isEqualTo(50_000);
    }
}
