package controller;

import com.googlecode.vkapi.VkApi;
import com.googlecode.vkapi.domain.VkOAuthToken;
import com.googlecode.vkapi.exceptions.VkException;
import com.googlecode.vkapi.exceptions.VkTokenExpiredException;
import model.User;
import model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import utils.VkontakteApi;

import java.security.Principal;
import java.sql.SQLException;

/**
 * Created by deepnekro on 22.07.15.
 */
@Controller
public class VkController {
    private final VkApi vkApi;
    static ApplicationContext applicationContext;
    static{
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Autowired
    public VkController(VkApi vkApi) {
        this.vkApi = vkApi;
    }

    @RequestMapping(value = "/vk/auth", method = RequestMethod.GET)
    public String auth() {
        return "redirect:" + VkontakteApi.getAuthUri();
    }

    @RequestMapping(value = "/vk/process", method = RequestMethod.GET)
    public String process(@RequestParam(value = "code", required = false) String code, ModelMap modelMap) throws VkException {

        if (code != null) {
            String token = VkontakteApi.getToken(code);
            User user = VkontakteApi.getUserByToken(token);
            modelMap.addAttribute("token", user);
            UserDAO userDAO = (UserDAO)applicationContext.getBean("userDao");
            try {
                userDAO.addUser(user);
            }catch (SQLException e){
                e.printStackTrace();
            }


        }

        return "vk";
    }

    @ExceptionHandler(VkTokenExpiredException.class)
    public ModelAndView handleVkTokenExpiredException(Principal principal, VkTokenExpiredException ex) {
        // handle as appropriately
        return null;
    }
}
