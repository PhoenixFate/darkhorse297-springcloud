package com.example.feign;

import com.example.pojo.User;
import org.springframework.stereotype.Component;


@Component
public class UserClientFallback implements UserClient{
    @Override
    //熔断的业务逻辑
    public User queryById(Integer id) {
        User user=new User();
        user.setUsername("未知用户");
        return user;
    }
}
