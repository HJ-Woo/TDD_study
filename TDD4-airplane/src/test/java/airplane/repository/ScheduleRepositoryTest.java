package airplane.repository;

import airplane.domain.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ScheduleRepositoryTest {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    void 항공사_id로_모두조회() {
        //given
        Long airLineId = 1L;

        //when
        List<Schedule> scheduleList = scheduleRepository.findAllByAirline_Id(airLineId);

        //then
        assertThat(scheduleList.size()).isEqualTo(2);
        scheduleList.forEach(schedule -> {
            assertThat(schedule.getAirline().getId()).isEqualTo(airLineId);
        });
    }
}
