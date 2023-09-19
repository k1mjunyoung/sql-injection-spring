package sqlinjectionspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sqlinjectionspring.entity.User;
import sqlinjectionspring.form.AttackForm;
import sqlinjectionspring.service.MainService;
import sqlinjectionspring.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "test", description = "테스트 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;

    private String wholeQuery;
    private String Query;
    private List<Object> result;

    @GetMapping("/user")
    public List<Object> test() {

        List<Object> user = this.userService.getUsers("'test01' or id is not NULL");

        return user;
    }

    @PostMapping("/attack")
    public ResponseEntity<?> attack(@RequestParam("param") String param) {

        this.wholeQuery = "select * from user where id= '" + param + "' ";

        try {
            result = this.userService.getUsers(param);
        }
        catch (BadSqlGrammarException e) {
            return new ResponseEntity<>("Bad SQL Grammer.", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Unknown Exception.", HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/result"));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/result")
    public List<Object> result() throws JsonProcessingException {

        // String resultJson = toJson(result);

        return result;
    }
}
