package com.example.mybatisplusdemo.pigxx.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplusdemo.pigxx.entity.SysUser;
import com.example.mybatisplusdemo.pigxx.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author shenming
 * @since 2019-06-16
 */
@RestController
@RequestMapping("/pigxx/sys-user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("list")
    public R getList(){

        QueryWrapper<SysUser> queryWrapper =  new QueryWrapper<>();
        queryWrapper.orderByDesc("user_id");

        Page<SysUser> page = new Page<>(1,5);  // 查询第1页，每页返回5条
        IPage<SysUser> iPage = sysUserService.page(page,queryWrapper);
        System.out.println(iPage.getRecords().size());

//        //1、使用JSONObject
//        JSONObject json = JSONObject.fromObject(iPage);
//        //2、使用JSONArray
//        JSONArray array=JSONArray.fromObject(iPage);
//
//        String strJson=json.toString();
//        String strArray=array.toString();
//
//        System.out.println("strJson:"+strJson);
//        System.out.println("strArray:"+strArray);

        return new R().setData(iPage).setCode(200).setMsg("success");
    }

}
