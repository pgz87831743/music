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
public class DailyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 宠物ID
     */
    private Long petId;

    @TableField(exist = false)
    private PetFile petFile;


    private String dataStr;

    private String petName;

    /**
     * 喂食量
     */
    private Double wsl;

    /**
     * 饮水量（推荐饮水量为体重的千克数乘六十，单位为毫升）
     */
    private Double ysl;

    /**
     * 刷牙（是或否）
     */
    private String sy;

    /**
     * 排便（是否正常）
     */
    private String pb;

    /**
     * 体重（今日是否变化，如有变化为多少）
     */
    private Double tz;

    /**
     * 活动量（大，中，小）
     */
    private String hdl;

    /**
     * 驱虫（是或否，如果距离上次驱虫时间小于一个月则发出驱虫频繁预警）
     */
    private String qc;

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
