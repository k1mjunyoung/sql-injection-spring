package sqlinjectionspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqlinjectionspring.entity.Query;

@Repository
public interface QueryRepository extends JpaRepository<Query, Integer> {

}
