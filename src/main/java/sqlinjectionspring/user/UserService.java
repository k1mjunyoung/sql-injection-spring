package sqlinjectionspring.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(String id, String password, String name, String email) {
        User user = new User();

        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);

        this.userRepository.save(user);

        return user;
    }
}
