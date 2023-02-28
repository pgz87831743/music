package jx.pgz.dao.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author 
 * @since 2023-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限类型 mean button
     */
    private String type;

    /**
     * 路径
     */
    private String url;

    /**
     * 父id
     */
    private String pid;


}
