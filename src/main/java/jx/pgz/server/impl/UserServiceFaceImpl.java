package jx.pgz.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.mapper.SysUserMapper;
import jx.pgz.server.UserServiceFace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceFaceImpl implements UserServiceFace {

    private final SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(queryWrapper);
    }

}
