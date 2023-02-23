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
 * 留言管理
 * </p>

 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 留言时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 留言人
     */
    private Long createByFrom;
    @TableField(exist = false)
    private SysUser createByFromUser;

    /**
     * 接收人
     */
    private Long createByTo;
    @TableField(exist = false)
    private SysUser createByToUser;


}
