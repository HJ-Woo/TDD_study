package airplane.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private Long reservationId;
    private Long userId;
    private int amount;
    private String account;

}
