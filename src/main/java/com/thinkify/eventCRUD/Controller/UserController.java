package com.thinkify.eventCRUD.Controller;

import com.thinkify.eventCRUD.Model.UserInfo;
import com.thinkify.eventCRUD.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/newUser")
    public ResponseEntity<String> addUser(@RequestBody UserInfo user){
        userService.saveUser(user);
        return ResponseEntity.ok("New user Added");
    }
}
