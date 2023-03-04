package jx.pgz.service.impl;

import jx.pgz.entity.Report;
import jx.pgz.mapper.ReportMapper;
import jx.pgz.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.model.dto.PageDTO;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * <p>
 * 检测表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
        @Override
        public Page<Report> page(PageDTO pageDTO) {
            return query()
                    .eq(StringUtils.hasText(pageDTO.getSearch()),"pass",pageDTO.getSearch())
                    .page(pageDTO.getMybatisPage());
        }
        @Override
        public List<Report> listAll() {
            return list();
        }


        @Override
        public boolean addReport(Report obj) {
            return save(obj);
        }

}
