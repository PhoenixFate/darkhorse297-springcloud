package com.example.mybatisplusdemo.pigxx.service.impl;

import com.example.mybatisplusdemo.pigxx.entity.SysLog;
import com.example.mybatisplusdemo.pigxx.mapper.SysLogMapper;
import com.example.mybatisplusdemo.pigxx.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author shenming
 * @since 2019-06-16
 */
@Service("SysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
