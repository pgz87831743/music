package jx.pgz.dao.yw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PetFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 种类（猫或狗就可以）
     */
    private String zl;

    /**
     * 年龄
     */
    private Float nl;

    /**
     * 体重（kg）
     */
    private Float qz;

    /**
     * 绝育情况（是否）
     */
    private String jy;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身长
     */
    private Float sc;

    /**
     * 疫苗情况（未免疫，未完全免疫，已完全免疫）
     */
    private String ymqk;

    /**
     * 过往病史（填写文字）
     */
    private String gwbs;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
