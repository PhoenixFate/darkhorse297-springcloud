package com.example.mybatisplusdemo.pigxx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author shenming
 * @since 2019-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    private String password;

    /**
     * 随机盐
     */
    private String salt;

    /**
     * 简介
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 0-正常，9-锁定
     */
    private String lockFlag;

    /**
     * 0-正常，1-删除
     */
    private String delFlag;

    /**
     * 微信openid
     */
    private String wxOpenid;

    /**
     * QQ openid
     */
    private String qqOpenid;

    private String giteeLogin;

    private String oscId;

    /**
     * 所属租户
     */
    private Integer tenantId;

    /**
     * 我的南京openid
     */
    private String nanjingOpenid;


}
