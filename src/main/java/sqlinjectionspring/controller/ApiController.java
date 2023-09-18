package sqlinjectionspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sqlinjectionspring.service.ApiService;
import sqlinjectionspring.user.User;
import sqlinjectionspring.user.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "test", description = "테스트 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService;
    private final UserService userService;

    @Operation(summary = "get apis", description = "해시맵 가져오기")
    @GetMapping
    public Map<String, Object> test() {

        return apiService.getData();

    }

    @GetMapping("/users")
    public Map<String, Object> users() {

        Map<String, Object> map = new HashMap<>();

        map.put(userService.getList().get(0).getId(), userService.getList().get(0));

        return map;
    }
}
