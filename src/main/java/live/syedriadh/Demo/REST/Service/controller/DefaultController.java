package live.syedriadh.Demo.REST.Service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    @Value("${SWAGGER_UI}")
    private String swaggerUI;

    @RequestMapping("/")
    public String redirect() {
        return "redirect:" +swaggerUI;
    }
}
