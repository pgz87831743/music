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
 * 新闻管理
 * </p>

 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NewsManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻内容
     */
    private String content;

    /**
     * 浏览次数
     */
    private Long times;

    /**
     * 发布时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 发布人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(exist = false)
    private SysUser createByUser;


}
