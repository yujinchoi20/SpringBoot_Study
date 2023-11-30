package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Controller
@Slf4j
public class ResponseBodyController {

    /**
     * HttpServletResponse 객체를 통해 HTTP 메시지 바디에 직접 응답 메시지를 전달
     * response.getWriter().write("ok");
     */
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * ResponseEntity: HTTP 응답 코드를 프로그래밍 할 수 있다.
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * @ResponseBody를 사용하여 view를 사용하지 않고, Http 메시지 컨버터를 통해 HTTP 메시지를 직접 입력
     */
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    /**
     * Http 메시지 컨버터를 통해 JSON 형식으로 변환되어 반환된다.
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("choi");
        helloData.setAge(23);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * @ResponseStatus(HttpStatus.OK)를 사용하여 응답 코드 설정
     * 응답 코드를 동적으로 변경하려면 ResponseEntity를 사용하면 된다. 
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("choi");
        helloData.setAge(23);

        return helloData;
    }

    /**
     * @RestController: @Controller + @ResponseBody --> Rest API를 사용하는 컨트롤러
     */
}
