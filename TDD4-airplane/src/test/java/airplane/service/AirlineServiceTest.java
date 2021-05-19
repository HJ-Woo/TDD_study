package airplane.service;

import airplane.domain.Airline;
import airplane.repository.AirlineRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AirlineServiceTest {

    @Mock
    private AirlineRepository airlinerepository;

    @InjectMocks
    private AirlineService airlineService;

    private static Airline airline;

    @BeforeAll
    static void setUp(){
        airline = Airline.builder()
                .name("ㅇㅇ항공사")
                .build();
    }

    @Test
    void 항공사_생성() {
        //given
        given(airlinerepository.save(any())).willReturn(airline);

        //when
        Airline saveline = airlineService.save(airline);

        //then
        assertThat(saveline.getName()).isEqualTo(airline.getName());
    }

    @Test
    void 항공사_조회() {
        //given
        given(airlinerepository.findById(any())).willReturn(Optional.of(airline));
        Long airlineId = 1L;

        //when
        Airline findLine = airlineService.findOne(airlineId);

        //then
        assertThat(findLine.getName()).isEqualTo(airline.getName());
    }

    @Test
    void 항공사_모두_조회() {
        //given
        List<Airline> testAirlines = new ArrayList<>();
        testAirlines.add(airline);

        given(airlinerepository.findAll()).willReturn(testAirlines);

        //when
        List<Airline> resultList = airlineService.findAll();

        //then
        assertThat(resultList).contains(airline);
    }
}
