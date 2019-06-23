package com.example.mybatisplusdemo.pigxx.service.impl;

import com.example.mybatisplusdemo.pigxx.entity.SysUser;
import com.example.mybatisplusdemo.pigxx.mapper.SysUserMapper;
import com.example.mybatisplusdemo.pigxx.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author shenming
 * @since 2019-06-16
 */
@Service("SysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
