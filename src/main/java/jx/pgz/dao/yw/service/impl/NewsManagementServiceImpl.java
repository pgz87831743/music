package jx.pgz.dao.yw.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.sys.entity.SysUser;
import jx.pgz.dao.sys.service.SysUserService;
import jx.pgz.dao.yw.entity.NewsManagement;
import jx.pgz.dao.yw.entity.SportsClocking;
import jx.pgz.dao.yw.mapper.NewsManagementMapper;
import jx.pgz.dao.yw.service.NewsManagementService;
import jx.pgz.enums.RoleTypeEnum;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 新闻管理 服务实现类
 * </p>
 */
@Service
@RequiredArgsConstructor
public class NewsManagementServiceImpl extends ServiceImpl<NewsManagementMapper, NewsManagement> implements NewsManagementService {

    private final SysUserService sysUserService;

    @Override
    public boolean saveData(NewsManagement management) {
        management.setCreateBy(UserContext.getInstance().getUserId());
        management.setTimes(0L);
        management.setCreateTime(LocalDateTime.now());
        return save(management);
    }

    @Override
    public Page<NewsManagement> pageQuery(PageDTO pageDTO) {
        String role = UserContext.getInstance().getUserRole();
        Set<Long> userIds = new HashSet<>();
        Page<NewsManagement> page;
        if (role.equals(RoleTypeEnum.ADMIN.name())) {
            page = lambdaQuery().
                    like(StringUtils.hasText(pageDTO.getSearch()), NewsManagement::getContent, pageDTO.getSearch()).
                    page(pageDTO.getMybatisPage());
        } else {
            page = lambdaQuery()
                    .eq(NewsManagement::getCreateBy, UserContext.getInstance().getUserId()).
                    like(StringUtils.hasText(pageDTO.getSearch()), NewsManagement::getContent, pageDTO.getSearch()).
                    page(pageDTO.getMybatisPage());
        }
        if (page.getTotal() > 0) {
            for (NewsManagement record : page.getRecords()) {
                userIds.add(record.getCreateBy());
            }
            Map<Long, SysUser> userMap = sysUserService.lambdaQuery().in(SysUser::getId, userIds).list().stream().collect(Collectors.toMap(SysUser::getId, v -> v));
            for (NewsManagement record : page.getRecords()) {
                record.setCreateByUser(userMap.get(record.getCreateBy()));
            }
        }
        return page;
    }

    @Override
    public List<NewsManagement> listQuery() {
        Set<Long> userIds = new HashSet<>();
        List<NewsManagement> list = lambdaQuery().list();
        if (list.size() > 0) {
            for (NewsManagement record : list) {
                userIds.add(record.getCreateBy());
            }
            Map<Long, SysUser> userMap = sysUserService.lambdaQuery().in(SysUser::getId, userIds).list().stream().collect(Collectors.toMap(SysUser::getId, v -> v));
            for (NewsManagement record : list) {
                record.setCreateByUser(userMap.get(record.getCreateBy()));
            }
        }
        return list;
    }

}
