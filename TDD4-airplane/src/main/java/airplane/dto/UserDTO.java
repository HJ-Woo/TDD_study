package airplane.dto;

import airplane.domain.User;
import airplane.domain.UserGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private Long airlineId;
    private String email;
    private double mileage;
    private UserGrade grade;

    public UserDTO(User user) {
        this.id = user.getId();
        this.airlineId = user.getAirline().getId();
        this.email = user.getEmail();
        this.mileage = user.getMileage();
        this.grade = user.getGrade();
    }
}
