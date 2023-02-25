package jx.pgz.dao.yw.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.yw.entity.ReportForms;
import jx.pgz.dao.yw.mapper.ReportFormsMapper;
import jx.pgz.dao.yw.service.ReportFormsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.model.dto.PageDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 培训报表 服务实现类
 * </p>

 */
@Service
public class ReportFormsServiceImpl extends ServiceImpl<ReportFormsMapper, ReportForms> implements ReportFormsService {

    @Override
    public Page<ReportForms> pageQuery(PageDTO pageDTO) {
        Page<ReportForms> page = lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getSearch()), ReportForms::getF2, pageDTO.getSearch())
                .page(pageDTO.getMybatisPage());
        return page;
    }
}
