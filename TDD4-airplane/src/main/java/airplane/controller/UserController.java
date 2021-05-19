package airplane.controller;

import airplane.domain.User;
import airplane.dto.UserDTO;
import airplane.dto.UserJoinDTO;
import airplane.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("new")
    public ResponseEntity<UserDTO> join(@RequestBody UserJoinDTO joinDTO) {
        User user = User.createBuilder()
                .userJoinDTO(joinDTO)
                .build();

        User save = userService.save(user, joinDTO.getAirlineId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserDTO(save));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> searchId(@PathVariable Long id) {
        return ResponseEntity.ok(new UserDTO(userService.findOne(id)));
    }
}
