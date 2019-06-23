package com.example.mybatisplusdemo.pigxx.service.impl;

import com.example.mybatisplusdemo.pigxx.entity.SysDept;
import com.example.mybatisplusdemo.pigxx.mapper.SysDeptMapper;
import com.example.mybatisplusdemo.pigxx.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author shenming
 * @since 2019-06-16
 */
@Service("SysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

}
