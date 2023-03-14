package jx.pgz.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role_authority")
@ApiModel(value = "SysRoleAuthority对象", description = "角色权限表")
public class SysRoleAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
     @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("用户id")
    private String authorityId;

    @ApiModelProperty("角色id")
    private String roleId;


}
