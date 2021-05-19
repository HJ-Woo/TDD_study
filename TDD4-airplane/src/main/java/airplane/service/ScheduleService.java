package airplane.service;

import airplane.Message;
import airplane.domain.Airline;
import airplane.domain.Schedule;
import airplane.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AirlineService airlineService;

    @Transactional
    public Schedule save(Schedule schedule, Long airlineId) {
        Airline airline = airlineService.findOne(airlineId);
        Schedule saveSchedule = Schedule.defaultBuilder()
                .airline(airline)
                .schedule(schedule)
                .build();
        return scheduleRepository.save(saveSchedule);
    }

    @Transactional
    public Schedule update(Long scheduleId, Schedule schedule) {
        Schedule findSchedule = findOne(scheduleId);

        findSchedule.update(schedule);

        return findSchedule;
    }

    @Transactional
    public void delete(long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    public Schedule findOne(Long scheduleId){
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException(Message.EMPTY_SCHEDULE));
    }

    public List<Schedule> searchAirline(Long airlineId) {
        return scheduleRepository.findAllByAirline_Id(airlineId);
    }
}
