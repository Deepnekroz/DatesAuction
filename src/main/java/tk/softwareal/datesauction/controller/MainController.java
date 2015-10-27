package tk.softwareal.datesauction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by deepnekro on 22.07.15.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(ModelMap model){
        return "test";
    }
}
