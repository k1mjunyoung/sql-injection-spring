package sqlinjectionspring.repository;

import org.apache.ibatis.annotations.Mapper;
import sqlinjectionspring.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectById(String id);

}
