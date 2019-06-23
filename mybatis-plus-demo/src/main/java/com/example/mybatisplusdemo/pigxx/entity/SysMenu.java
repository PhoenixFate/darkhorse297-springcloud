package com.example.mybatisplusdemo.pigxx.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author shenming
 * @since 2019-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限标识
     */
    private String permission;

    /**
     * 前端URL
     */
    private String path;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * VUE页面
     */
    private String component;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 0-开启，1- 关闭
     */
    private String keepAlive;

    /**
     * 菜单类型 （0菜单 1按钮）
     */
    private String type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标记(0--正常 1--删除)
     */
    private String delFlag;


}
