package web.controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;
import service.UserServiceHibernate;

import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "users", method = { RequestMethod.GET, RequestMethod.POST })
    public String getUsers(ModelMap model) {
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
    public String editUser(@ModelAttribute("user") User user) {
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "redirect:./users";
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute("user") User user) {
        try {
            userService.deleteUser(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "redirect:./users";
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addUserView() {
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        try {
            userService.addUser(user.getName(), user.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "redirect:./users";
        }
    }
}
