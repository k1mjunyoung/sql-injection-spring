package sqlinjectionspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqlinjectionspring.entity.User;
import sqlinjectionspring.repository.UserMapper;
import sqlinjectionspring.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getList() {
        return this.userRepository.findAll();
    }

    public List<Object> getUsers(String id) {
        return userMapper.selectById(id);
    }

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
