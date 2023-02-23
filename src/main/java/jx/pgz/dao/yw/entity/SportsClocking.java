package jx.pgz.dao.yw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import jx.pgz.dao.sys.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 运动打卡
 * </p>

 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SportsClocking implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 打卡地点
     */
    private String address;

    /**
     * 打卡描述
     */
    private String content;

    /**
     * 打卡时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 打卡人物
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(exist = false)
    private SysUser createByUser;


}
