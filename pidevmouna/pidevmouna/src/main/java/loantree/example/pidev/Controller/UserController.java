package loantree.example.pidev.Controller;
import loantree.example.pidev.Entities.User;
import loantree.example.pidev.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    //http://localhost:8080/user/retrieve-user
    @GetMapping("/retrieve-user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    //http://localhost:8080/user/get-user/{id}
    @GetMapping("/get-user/{user-id}")
    public User getUserById(@PathVariable("user-id") Integer IdUser) {
        return userService.getUserById(IdUser);
    }
    //http://localhost:8080/user/create-user
    @PostMapping("/createUser")
    public User createUser(@RequestBody User u) {
        return userService.createUser(u);
    }
    //http://localhost:8080/user/update/{user-id}
    @PutMapping("/update/{user-id}")
    public User updateUser(@PathVariable ("user-id") Integer UserId, @RequestBody User user) {
        return userService.updateUser(UserId, user);
    }
    //http://localhost:8080/user/delete/{UserId}
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable ("id") Integer IdUser) {
        userService.deleteUser(IdUser);
    }
}


