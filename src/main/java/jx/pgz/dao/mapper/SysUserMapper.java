package jx.pgz.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jx.pgz.dao.entity.SysAuthority;
import jx.pgz.dao.entity.SysRole;
import jx.pgz.dao.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    @Select("select *\n" +
            "from sys_role\n" +
            "where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username = #{username}))")
    List<SysRole> getRoleByUsername(@Param("username") String username);


    @Select("select *\n" +
            "from sys_authority\n" +
            "where id in (\n" +
            "    select authority_id from sys_role_authority where  role_id in(\n" +
            "        select id\n" +
            "        from sys_role\n" +
            "        where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username = #{username}))\n" +
            "        )\n" +
            "    )\n")
    List<SysAuthority> getAuthorityByUsername(@Param("username") String username);


    @Select("select max(id)+1 from ${tableName}")
    Long nextId(@Param("tableName") String tableName);

}
