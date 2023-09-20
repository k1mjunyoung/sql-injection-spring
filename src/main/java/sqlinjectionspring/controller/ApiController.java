package sqlinjectionspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.*;
import sqlinjectionspring.service.UserService;

import java.util.List;

@Tag(name = "API", description = "API 명세")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;

    @Operation(summary = "테스트 API", description = "백엔드 테스트용 API로 무시하셔도 됩니다.")
    @GetMapping("/test")
    public List<Object> test() {

        List<Object> user = this.userService.getUsers("'test01' or id is not NULL");

        return user;
    }

    @Operation(summary = "공격 API")
    @Parameter(name = "param", description = "인젝션 공격에 사용될 문자열")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "400", description = "SQL 문법 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/attack")
    public ResponseEntity attack(@RequestParam("param") String param) {

        // String wholeQuery = new String("select * from user where id= '" + param + "' ");

        List<Object> result;

        try {
            result = this.userService.getUsers(param);
        }
        catch (BadSqlGrammarException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().body(result);
    }
}
