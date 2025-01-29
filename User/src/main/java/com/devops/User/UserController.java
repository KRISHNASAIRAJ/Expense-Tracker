package com.devops.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        boolean success = userService.userRegister(user);
        return success ? ResponseEntity.ok("User Registered Successfully") : ResponseEntity.badRequest().body("User Already Exists");
    }

        @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean success=userService.userLogin(user);
        return success?ResponseEntity.ok("Login Successfully"):ResponseEntity.badRequest().body("Login Failed Please check your username and password");
    }
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody User user) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getHashedPassword()));
//            User user1 = userService.findUserByEmailId(user.getEmailId());
//            String jwt = jwtUtil.generateToken(user1.getEmailId());
//            return new ResponseEntity<>(jwt, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);
//        }
//    }
}