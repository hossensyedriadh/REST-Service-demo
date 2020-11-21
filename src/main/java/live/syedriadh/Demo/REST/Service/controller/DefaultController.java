package live.syedriadh.Demo.REST.Service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    @RequestMapping("/")
    public String redirect() {
        return "redirect:http://localhost:8080/swagger-ui/";
    }
}
