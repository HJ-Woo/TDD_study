package airplane.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDTO {
    private Long airlineId;
    private String email;
    private String password;
}
