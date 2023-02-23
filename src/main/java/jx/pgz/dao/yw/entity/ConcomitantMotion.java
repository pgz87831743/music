package jx.pgz.dao.yw.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.handler.JsonArrayHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 相约运动
 * </p>

 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
public class ConcomitantMotion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 相约地点
     */
    private String address;

    /**
     * 相约描述
     */
    private String content;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 相约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime concomitantTime;

    /**
     * 发起人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(exist = false)
    private SysUser createByUser;

    /**
     * 报名人员
     */
    @TableField(typeHandler = JsonArrayHandler.class,value = "concomitant_person")
    private List<String> concomitantPerson;


    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(Arrays.asList("root")));
        System.out.println(JSON.parseArray(JSON.toJSONString(Arrays.asList("root")),String.class));
    }

}
