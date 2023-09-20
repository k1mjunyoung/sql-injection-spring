package sqlinjectionspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.*;
import sqlinjectionspring.service.UserService;

import java.net.URI;
import java.util.List;

@Tag(name = "API", description = "API 명세")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;
    private List<Object> result;

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
    public ResponseEntity<?> attack(@RequestParam("param") String param) {

        // String wholeQuery = new String("select * from user where id= '" + param + "' ");

        try {
            result = this.userService.getUsers(param);
        }
        catch (BadSqlGrammarException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/result"));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @Operation(summary = "결과 APi", description = "객체 리스트가 반환됩니다.")
    @GetMapping("/result")
    public List<Object> result() throws JsonProcessingException {
        return result;
    }
}
