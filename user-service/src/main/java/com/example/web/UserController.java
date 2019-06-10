package com.example.web;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Integer id){
        try {
            Thread.sleep(5000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return userService.findByQueryById(id);
    }

}
