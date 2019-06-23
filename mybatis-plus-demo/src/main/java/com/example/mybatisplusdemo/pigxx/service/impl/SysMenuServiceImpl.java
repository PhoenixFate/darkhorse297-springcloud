package com.example.mybatisplusdemo.pigxx.service.impl;

import com.example.mybatisplusdemo.pigxx.entity.SysMenu;
import com.example.mybatisplusdemo.pigxx.mapper.SysMenuMapper;
import com.example.mybatisplusdemo.pigxx.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author shenming
 * @since 2019-06-16
 */
@Service("SysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

}
