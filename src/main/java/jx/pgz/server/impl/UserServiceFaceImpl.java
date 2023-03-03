package jx.pgz.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jx.pgz.dao.sys.entity.SysAuthority;
import jx.pgz.dao.sys.entity.SysRole;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.mapper.SysUserMapper;
import jx.pgz.dao.sys.service.SysAuthorityService;
import jx.pgz.dao.sys.service.SysRoleService;
import jx.pgz.server.UserServiceFace;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceFaceImpl implements UserServiceFace {

    private final SysUserMapper sysUserMapper;
    private final SysRoleService sysRoleService;
    private final SysAuthorityService sysAuthorityService;

    @Override
    public SysUser getUserByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public List<SysRole> getRoleByUsername(String username) {
        return sysUserMapper.getRoleByUsername(username);
    }

    @Override
    public List<SysRole> roleList(String username) {
        Map<Long, SysRole> roleMap = getRoleByUsername(username).stream().collect(Collectors.toMap(SysRole::getId, s -> s));
        List<SysRole> list = sysRoleService.list();
        for (SysRole sysRole : list) {
            if (roleMap.containsKey(sysRole.getId())) {
                sysRole.setCheck(true);
            }
        }
        return list;
    }

    @Override
    public List<SysAuthority> getAuthorityByUsername(String username) {
        return sysUserMapper.getAuthorityByUsername(username);
    }

    @Override
    public List<SysAuthority> authorityTree() {
        List<SysAuthority> list = sysAuthorityService.list();
        List<SysAuthority> removeList=new ArrayList<>();
        for (SysAuthority sysAuthority : list) {
            List<SysAuthority> collect = list.stream().filter(s -> s.getPid().equals(sysAuthority.getId())).collect(Collectors.toList());
            if (!collect.isEmpty()){
                removeList.addAll(collect);
            }
            sysAuthority.setChildren(collect);
        }
        list.removeAll(removeList);
        return list;
    }
}
