package sqlinjectionspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sqlinjectionspring.entity.Query;
import sqlinjectionspring.repository.QueryRepository;

@Service
@RequiredArgsConstructor
public class QueryService {
    private final QueryRepository queryRepository;

    public void saveQuery(String query) {
        Query q = new Query();
        q.setQuery(query);
        this.queryRepository.save(q);
    }
}
