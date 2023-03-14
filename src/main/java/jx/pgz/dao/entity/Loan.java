package jx.pgz.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 贷款信息表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "Loan对象", description = "贷款信息表")
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("卡号")
    private String cardNo;

    @ApiModelProperty("贷款金额")
    private Double amount;

    @ApiModelProperty("已还金额")
    private Double amountPaid;

    @ApiModelProperty("利息")
    private Double interest;

    @ApiModelProperty("还款日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate repaymentDate;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("信审人员 1通过/ 0驳回")
    private String xdSh;

    @ApiModelProperty("财务人员 1已放款/0未放款")
    private String cwSh;

    @ApiModelProperty("是否延期 1逾期 0未逾期")
    private String postpone;


    @TableField(exist = false)
    private SysUser sysUser;

    @TableField(exist = false)
    @ApiModelProperty("还款金额")
    private Double ret;


    @ApiModelProperty("还款状态 0已还清 1还款中")
    private String hkZt;



}
