package jx.pgz.server.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jx.pgz.dao.sys.entity.*;
import jx.pgz.dao.sys.mapper.SysUserMapper;
import jx.pgz.dao.sys.service.SysAuthorityService;
import jx.pgz.dao.sys.service.SysRoleAuthorityService;
import jx.pgz.dao.sys.service.SysRoleService;
import jx.pgz.dao.sys.service.SysUserRoleService;
import jx.pgz.model.dto.AssignAuthorityDTO;
import jx.pgz.model.dto.AssignRolesDTO;
import jx.pgz.server.UserServiceFace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceFaceImpl implements UserServiceFace {

    private final SysUserMapper sysUserMapper;
    private final SysRoleService sysRoleService;
    private final SysAuthorityService sysAuthorityService;
    private final SysUserRoleService sysUserRoleService;
    private final SysRoleAuthorityService sysRoleAuthorityService;

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
        Map<String, SysRole> roleMap = getRoleByUsername(username).stream().collect(Collectors.toMap(SysRole::getId, s -> s));
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
        return toTree(list);
    }

    @Override
    public Object authorityTreeByRoleId(Long roleId) {
        List<SysAuthority> list = sysAuthorityService.list();
        List<SysAuthority> check = new ArrayList<>();
        Set<String> roleHasAuth = sysRoleAuthorityService.lambdaQuery().eq(SysRoleAuthority::getRoleId, roleId).list().stream().map(SysRoleAuthority::getAuthorityId).collect(Collectors.toSet());
        for (SysAuthority sysAuthority : list) {
            if (roleHasAuth.contains(sysAuthority.getId())) {
                sysAuthority.setCheck(true);
                check.add(JSON.parseObject(JSON.toJSONString(sysAuthority),SysAuthority.class));
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("tree",toTree(list));
        map.put("check",check);
        return map;
    }

    private List<SysAuthority> toTree(List<SysAuthority> list) {
        List<SysAuthority> removeList = new ArrayList<>();
        for (SysAuthority sysAuthority : list) {
            List<SysAuthority> collect = list.stream().filter(s -> s.getPid().equals(sysAuthority.getId())).collect(Collectors.toList());
            if (!collect.isEmpty()) {
                removeList.addAll(collect);
            }
            sysAuthority.setChildren(collect);
        }
        list.removeAll(removeList);
        return list;
    }


    @Override
    @Transactional
    public boolean assignRoles(AssignRolesDTO assignRolesDTO) {

        sysUserRoleService.lambdaUpdate()
                .eq(SysUserRole::getUserId, assignRolesDTO.getUserId())
                .remove();

        Set<String> roles = assignRolesDTO.getRoles();
        if (!roles.isEmpty()) {
            Set<SysUserRole> sysUserRoles = roles.stream().map(s -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(assignRolesDTO.getUserId());
                sysUserRole.setRoleId(s);
                return sysUserRole;
            }).collect(Collectors.toSet());
            return sysUserRoleService.saveBatch(sysUserRoles);
        }
        return false;
    }

    @Override
    public boolean assignAuthority(AssignAuthorityDTO authorityDTO) {
        sysRoleAuthorityService.lambdaUpdate()
                .eq(SysRoleAuthority::getRoleId, authorityDTO.getRoleId())
                .remove();
        Set<String> authority = authorityDTO.getAuthority();
        if (!authority.isEmpty()) {
            Set<SysRoleAuthority> sysUserRoles = authority.stream().map(s -> {
                SysRoleAuthority sysRoleAuthority = new SysRoleAuthority();
                sysRoleAuthority.setRoleId(authorityDTO.getRoleId());
                sysRoleAuthority.setAuthorityId(s);
                return sysRoleAuthority;
            }).collect(Collectors.toSet());
            return sysRoleAuthorityService.saveBatch(sysUserRoles);
        }
        return false;
    }

    @Override
    public Long nextId(String tableName) {
        Long nextId = sysUserMapper.nextId(tableName);
        return nextId==null?1L:nextId;
    }
}
