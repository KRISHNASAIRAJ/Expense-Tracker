package com.devops.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        boolean success=userService.userRegister(user);
        return success? ResponseEntity.ok("User Registered Successfully"):ResponseEntity.badRequest().body("User Already Exists");
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean success=userService.userLogin(user);
        return success?ResponseEntity.ok("Login Successfully"):ResponseEntity.badRequest().body("Login Failed Please check your username and password");
    }
}
