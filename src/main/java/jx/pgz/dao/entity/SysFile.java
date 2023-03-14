package jx.pgz.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
     @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * md5
     */
    private String md5;

    /**
     * 文件路径
     */
    private String path;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;


    @TableField(exist = false)
    private String url;


}
