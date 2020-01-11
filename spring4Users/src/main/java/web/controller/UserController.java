package web.controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import service.UserService;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "users", method = { RequestMethod.GET, RequestMethod.POST })
    public String getUsers(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        model.addAttribute("users", userService.getAllUser());
        return "users";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editUserView(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        try {
            model.addAttribute("user", userService.getUserById(Long.valueOf(allRequestParams.get("id"))));
            return "edit";
        } catch (Exception e) {
            return "rediect:./users";
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editUser(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        try {
            User user = userService.getUserById(Long.valueOf(allRequestParams.get("id")));
            user.setAge(Integer.valueOf(allRequestParams.get("age")));
            user.setName(allRequestParams.get("name"));
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "redirect:./users";
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        try {
            userService.deleteUser(Long.valueOf(allRequestParams.get("id")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "redirect:./users";
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addUserView(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        try {
            userService.addUser(allRequestParams.get("name"), Integer.valueOf(allRequestParams.get("age")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "redirect:./users";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String userPage(Authentication authentication, ModelMap model) {
        model.addAttribute("user", authentication.getPrincipal());
        return "user";
    }
}
