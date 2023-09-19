package sqlinjectionspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sqlinjectionspring.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);

    List<User> findAll();

    @Query(value = "select * from user as u where u.id = ?", nativeQuery = true)
    List<User> findAllQuery();

}
