package sqlinjectionspring.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Object> selectById(String id);

}
