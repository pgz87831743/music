package jx.pgz.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_authority")
@ApiModel(value = "SysAuthority对象", description = "权限表")
public class SysAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("权限名")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("权限类型 mean button")
    private String type;

    @ApiModelProperty("路径")
    private String url;

    @ApiModelProperty("父id")
    private String pid;

    @TableField(exist = false)
    private boolean check;

    @TableField(exist = false)
    private List<SysAuthority> children;


}
