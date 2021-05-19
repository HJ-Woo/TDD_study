package airplane.service;

import airplane.domain.Airline;
import airplane.domain.Reservation;
import airplane.domain.User;
import airplane.dto.UserJoinDTO;
import airplane.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private AirlineService airlineService;

    private static Airline airline;
    private static User user;
    private static User saveUser;
    private static String email = "testEmail";
    private static String password = "testPassword";

    @BeforeAll
    static void setUp() {
        airline = Airline.builder()
                .name("항공사1")
                .build();

        UserJoinDTO joinDTO = new UserJoinDTO(1L, email, password);

        user = User.createBuilder()
                .userJoinDTO(joinDTO)
                .build();

        saveUser = User.defaultBuilder()
                .airline(airline)
                .user(user)
                .build();
    }

    @Test
    void 유저를저장한다() {
        //given
        given(userRepository.save(any())).willReturn(saveUser);
        given(airlineService.findOne(any())).willReturn(airline);

        //when
        User resultUser = userService.save(user, 1L);

        //then
        checkUser(resultUser);
    }

    @Test
    void 유저정보를조회한다() {
        //given
        Long userId = 1L;
        given(userRepository.findById(any())).willReturn(Optional.of(saveUser));

        //when
        User findUser = userService.findOne(userId);

        //then
        checkUser(findUser);
    }

    void checkUser(User result) {
        assertThat(result.getEmail()).isEqualTo(saveUser.getEmail());
        assertThat(result.getPassword()).isEqualTo(saveUser.getPassword());
        assertThat(result.getMileage()).isEqualTo(0);
        assertThat(result.getAirline()).isEqualTo(saveUser.getAirline());
    }

    @Test
    void 특정사용자의예약목록을조회한다() {
        //given
        Long userId = 1L;
        Reservation testReservation = Reservation.createBuilder().price(1000).build();
        testReservation.setUser(user);
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        //when
        List<Reservation> reservationList = userService.findReservation(userId);

        //then
        assertThat(reservationList.size()).isEqualTo(1);
        assertThat(reservationList.get(0).getUser()).isEqualTo(user);
    }
}
