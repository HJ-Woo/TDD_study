package airplane.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ReservationRequest {
    private Long scheduleId;
    private Long userId;
    private Long seatId;
    private int price;
}
