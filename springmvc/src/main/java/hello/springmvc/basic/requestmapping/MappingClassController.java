package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String users() {
        return "get users";
    }

    @PostMapping
    public String addUser() {
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String data) {
        return "get userId = " + data;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String data) {
        return "update userId = " + data;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String data) {
        return "delete userId = " + data;
    }
}
