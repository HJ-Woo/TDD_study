package airplane.service;

import airplane.domain.Airline;
import airplane.domain.User;
import airplane.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static airplane.Message.EMPTY_USER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AirlineService airlineService;

    public User save(User saveUser, Long airlineId) {
        Airline airline = airlineService.findOne(airlineId);
        User user = User.defaultBuilder()
                .airline(airline)
                .user(saveUser)
                .build();
        return userRepository.save(user);
    }

    public User findOne(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(EMPTY_USER));
    }
}
