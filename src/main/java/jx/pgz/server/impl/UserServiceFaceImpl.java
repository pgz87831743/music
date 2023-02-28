package jx.pgz.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import jx.pgz.dao.sys.entity.SysAuthority;
import jx.pgz.dao.sys.entity.SysRole;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.mapper.SysUserMapper;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.server.UserServiceFace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceFaceImpl implements UserServiceFace {

    private final SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,username);
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public List<SysRole> getRoleByUsername(String username) {
        return sysUserMapper.getRoleByUsername(username);
    }

    @Override
    public List<SysAuthority> getAuthorityByUsername(String username) {
        return sysUserMapper.getAuthorityByUsername(username);
    }
}
