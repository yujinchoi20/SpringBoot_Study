package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@Slf4j
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //view 조회를 무시하고, HTTP 메시지 바디에 직접 해당 내용을 입력
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username = {}, age = {}", memberName, memberAge);

        return "ok";
    }

    @ResponseBody //view 조회를 무시하고, HTTP 메시지 바디에 직접 해당 내용을 입력
    @RequestMapping("/request-param-v3") //@RequestParam 속성 생략
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    @ResponseBody //view 조회를 무시하고, HTTP 메시지 바디에 직접 해당 내용을 입력
    @RequestMapping("/request-param-v4") //@RequestParam 생략
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    } //String, int, Integer 등 단순 타입은 @RequestParam 생략 가능!! -> 하지만 어노테이션까지 완전히 생략하는 것은 과함
      //@RequestParam 이 있으면 명확하게 요청 파라미터에서 데이터를 읽는 것을 알 수 있다.

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                       @RequestParam(defaultValue = "-1") Integer age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    } //@RequestParam 에 default 값을 적용할 경우 이미 기본 값이 있기 때문에 required는 의미가 없다.

    /**
     * @ModelAttribute
     *
     * @RequestParam String username; @RequestParam int age;
     * HelloData data = new HelloData();
     * data.setUsername(username);
     * data.setAge(age);
     *
     * --> @ModelAttribute 는 위의 과정을 자동화해준다!
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { //@ModelAttribute 생략 가능
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    } //하지만 @RequestParam 도 생략할 수 있어 혼란이 발생할 수 있다.
    //스프링은 String, int, Integer 같은 단순 타입은 @RequestParam, 나머지는 @ModelAttribute 로 적용된다.
}
