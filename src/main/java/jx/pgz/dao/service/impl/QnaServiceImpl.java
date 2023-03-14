package jx.pgz.dao.service.impl;

import jx.pgz.dao.entity.Qna;
import jx.pgz.dao.mapper.QnaMapper;
import jx.pgz.dao.service.QnaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.model.dto.PageDTO;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * <p>
 * 问答表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class QnaServiceImpl extends ServiceImpl<QnaMapper, Qna> implements QnaService {
        @Override
        public Page<Qna> page(PageDTO pageDTO) {
            return lambdaQuery()
                    .like(StringUtils.hasText(pageDTO.getSearch()),Qna::getQuestion,pageDTO.getSearch())
                    .page(pageDTO.getMybatisPage());
        }
        @Override
        public List<Qna> listAll() {
            return list();
        }
        @Override
        public Qna getQnaById(String id) {
            return getById(id);
        }
        @Override
        public boolean deleteQnaById(String id) {
            return removeById(id);
        }
        @Override
        public boolean addQna(Qna obj) {
            return save(obj);
        }
        @Override
        public boolean updateQnaById(Qna obj) {
            return updateById(obj);
        }
}
