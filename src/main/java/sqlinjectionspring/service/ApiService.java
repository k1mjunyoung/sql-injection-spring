package sqlinjectionspring.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApiService {
    public Map<String, Object> getData() {

        Map<String, Object> data = new HashMap<>();

        data.put("data1", 1);
        data.put("data2", 2);
        data.put("data3", 3);

        return data;
    }
}
