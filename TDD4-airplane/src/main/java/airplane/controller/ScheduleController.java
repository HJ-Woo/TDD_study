package airplane.controller;

import airplane.domain.Schedule;
import airplane.dto.ScheduleDTO;
import airplane.dto.ScheduleRequest;
import airplane.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("{airline_id}/schedule/new")
    public ResponseEntity<ScheduleDTO> create(@PathVariable("airline_id") Long airlineId, @RequestBody ScheduleRequest request) {
        Schedule schedule = Schedule.createBuilder()
                .scheduleRequest(request)
                .build();

        Schedule saveSchedule = scheduleService.save(schedule, airlineId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ScheduleDTO(saveSchedule));
    }

    @PostMapping("{schedule_id}/update")
    public ResponseEntity<ScheduleDTO> update(@PathVariable("schedule_id") Long scheduleId, @RequestBody ScheduleRequest request) {
        Schedule schedule = Schedule.createBuilder()
                .scheduleRequest(request)
                .build();
        Schedule updateSchedule = scheduleService.update(scheduleId, schedule);
        return ResponseEntity.ok(new ScheduleDTO(updateSchedule));
    }

    @DeleteMapping("{schedule_id}/delete")
    public ResponseEntity<Void> delete(@PathVariable("schedule_id") Long scheduleId) {
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping("{airline_id}/schedules")
    public ResponseEntity<List<ScheduleDTO>> searchAll(@PathVariable("airline_id") Long airlineId) {
        return ResponseEntity.ok(scheduleService.searchAirline(airlineId).stream()
                .map(ScheduleDTO::new)
                .collect(toList()));
    }
}
