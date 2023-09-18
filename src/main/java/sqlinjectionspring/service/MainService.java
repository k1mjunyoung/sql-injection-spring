package sqlinjectionspring.service;

import com.sun.istack.FinalArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqlinjectionspring.entity.Query;
import sqlinjectionspring.user.UserService;
import sqlinjectionspring.repository.QueryRepository;

import java.sql.Driver;

@Service
@RequiredArgsConstructor
public class MainService {

    private final UserService userService;
    private final QueryRepository queryRepository;

    public void saveQuery(Query query) {
        this.queryRepository.save(query);
    }

}
