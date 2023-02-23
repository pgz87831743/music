package jx.pgz.dao.yw.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.dao.yw.entity.MessageManagement;
import jx.pgz.dao.yw.mapper.MessageManagementMapper;
import jx.pgz.dao.yw.service.MessageManagementService;
import jx.pgz.enums.RoleTypeEnum;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 留言管理 服务实现类
 * </p>
 */
@Service
@RequiredArgsConstructor
public class MessageManagementServiceImpl extends ServiceImpl<MessageManagementMapper, MessageManagement> implements MessageManagementService {

    private final SysUserService sysUserService;

    @Override
    public Page<MessageManagement> pageQuery(PageDTO pageDTO) {
        String role = UserContext.getInstance().getUserRole();
        Set<Long> userIds = new HashSet<>();
        Page<MessageManagement> page;
        if (role.equals(RoleTypeEnum.ADMIN.name())) {
            page = lambdaQuery().
                    like(StringUtils.hasText(pageDTO.getSearch()), MessageManagement::getContent, pageDTO.getSearch()).
                    page(pageDTO.getMybatisPage());
        } else {
            page = lambdaQuery()
                    .eq(MessageManagement::getCreateByTo, UserContext.getInstance().getUserId()).
                    like(StringUtils.hasText(pageDTO.getSearch()), MessageManagement::getContent, pageDTO.getSearch()).
                    page(pageDTO.getMybatisPage());
        }
        if (page.getTotal() > 0) {
            for (MessageManagement record : page.getRecords()) {
                userIds.add(record.getCreateByFrom());
                userIds.add(record.getCreateByTo());
            }
            Map<Long, SysUser> userMap = sysUserService.lambdaQuery().in(SysUser::getId, userIds).list().stream().collect(Collectors.toMap(SysUser::getId, v -> v));
            for (MessageManagement record : page.getRecords()) {
                record.setCreateByFromUser(userMap.get(record.getCreateByFrom()));
                record.setCreateByToUser(userMap.get(record.getCreateByTo()));
            }
        }
        return page;
    }
}
