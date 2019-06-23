package com.example.web;

import com.example.pojo.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api(value = "eureka-provider", description = "用户查询接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    public User getUserById(@PathVariable("id") @ApiParam(value = "主键ID") Integer id){
//        try {
//            Thread.sleep(5000 );
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return userService.findByQueryById(id);
    }

    @PostMapping("/post")
    public User postUserById(@RequestParam(value="id",required = false) Integer id){
        System.out.println("--------id:  "+id);
//        try {
//            Thread.sleep(5000 );
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return userService.findByQueryById(id);
    }


}
