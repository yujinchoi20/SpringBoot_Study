package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController //반환 값으로 뷰를 찾는 것이 아니라 HTTP 메시지 바디에 바로 입력한다.
public class MappingController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /*
        기본 요청
        RequestMapping을 사용하기 때문에 GET, POST, PUT, PATCH, DELETE 등 HTTP 메서드 모두 허용
     */
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        logger.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        logger.info("mappingGetV1");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        logger.info("mappingGetV2");
        return "ok";
    }

    // @RequestMapping(value = "/request-get", method = RequestMethod.GET)
    // @GetMapping("/request-get")
    // --> 동일한 결과

    /*
        PathVariable 사용
        변수명이 같으면 생략 가능
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        logger.info("mapping userId = {}", userId);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        logger.info("mappingPath userId = {}, orderId = {}", userId, orderId);
        //mappingPath userId = choi, orderId = 123
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        logger.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        logger.info("mappingHeader");
        return "ok";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        logger.info("mappingConsumes");
        //Content-Type = application/json 추가
        return "ok";
    }

    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        logger.info("mappingProduces");
        return "ok";
    }
}
