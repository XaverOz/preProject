package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user-rest")
public class UserRestController {

    private RestTemplate restTemplate;

    @Autowired
    UserRestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/user/id/{id}")
    public User getUserById(@PathVariable int id) {
        return restTemplate.getForObject("http://127.0.0.1:8080/user-rest/user/id/" + String.valueOf(id), User.class);
    }

    @RequestMapping("/user/all")
    public List<User> getUsers() {
        return restTemplate.getForObject("http://127.0.0.1:8080/user-rest/user/all", List.class);
    }

    @RequestMapping("/role/all")
    public List<Role> getRoles() {
        return restTemplate.getForObject("http://127.0.0.1:8080/user-rest/role/all", List.class);
    }

    @RequestMapping("/user/add")
    public User addUser(@RequestBody User user) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("email", user.getEmail());
        map.put("login", user.getLogin());
        map.put("password", user.getPassword());
        map.put("roles", user.getRoles());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = new ObjectMapper().writeValueAsString(map);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        restTemplate.postForEntity("http://127.0.0.1:8080/user-rest/user/add", entity, String.class);
        return user;
    }

    @RequestMapping("/user/edit")
    public User editUser(@RequestBody User user) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("email", user.getEmail());
        map.put("login", user.getLogin());
        map.put("password", user.getPassword());
        map.put("roles", user.getRoles());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = new ObjectMapper().writeValueAsString(map);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        restTemplate.postForEntity("http://127.0.0.1:8080/user-rest/user/edit", entity, String.class);
        return user;
    }
}
