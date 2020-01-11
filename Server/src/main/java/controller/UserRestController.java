package controller;

import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user-rest")
public class UserRestController {
    UserService userService;

    @Autowired
    @Qualifier("userServiceHibernate")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/id/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/login/{login}")
    public User getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @GetMapping("/user/all")
    public List<User> getUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/role/all")
    public List<Role> getRoles() {
        return userService.getAllRole();
    }

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) {
        List<Role> roles = new ArrayList<>();
        user.getRoles().stream().forEach(a -> roles.add(userService.getRoleByName(a.getName())));
        user.setRoles(roles);
        userService.addUser(user);
        return user;
    }

    @PostMapping("/user/edit")
    public User editUser(@RequestBody User user) {
        List<Role> roles = new ArrayList<>();
        user.getRoles().stream().forEach(a -> roles.add(userService.getRoleByName(a.getName())));
        user.setRoles(roles);
        userService.updateUser(user);
        return user;
    }
}
