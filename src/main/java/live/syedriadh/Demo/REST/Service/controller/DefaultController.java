package live.syedriadh.Demo.REST.Service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {
    @Value("${HTTP.HEADER_NAME}")
    private String headerName;

    @Value("${HTTP.API_KEY}")
    private String headerValue;

    @Value("${application_version}")
    private String appVersion;

    @Value("${SWAGGER_UI}")
    private String swaggerUI;

    @RequestMapping("/")
    public ModelAndView redirect(Model model) {
        model.addAttribute("app_version", appVersion);
        model.addAttribute("header_name", headerName);
        model.addAttribute("header_value", headerValue);

        return new ModelAndView("index");
    }

    @RequestMapping("/swagger")
    public String swagger() {
        return "redirect:/" +swaggerUI;
    }
}
