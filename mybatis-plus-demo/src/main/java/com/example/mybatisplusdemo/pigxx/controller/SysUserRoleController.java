package com.example.mybatisplusdemo.pigxx.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.mybatisplusdemo.pigxx.entity.SysUserRole;
import com.example.mybatisplusdemo.pigxx.service.SysUserRoleService;
import com.example.mybatisplusdemo.pigxx.service.impl.SysUserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author shenming
 * @since 2019-06-16
 */
@RestController
@RequestMapping("/pigxx/sys-user-role")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService  sysUserRoleService;

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    public List<SysUserRole> list(@RequestParam Map<String, Object> params){
        List<SysUserRole> list = sysUserRoleService.list();

        return list;
    }

}
