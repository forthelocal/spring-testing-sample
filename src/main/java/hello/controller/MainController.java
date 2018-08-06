package hello.controller;

import hello.data.UserMapper;
import hello.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {

  private final UserMapper userMapper;

  public MainController(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @GetMapping(path = "/add") // Map ONLY GET Requests
  public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userMapper.insert(n);
    return "Saved";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userMapper.findAll();
  }
}
