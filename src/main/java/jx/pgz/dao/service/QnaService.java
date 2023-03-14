package jx.pgz.dao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jx.pgz.dao.entity.Qna;
import jx.pgz.model.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 问答表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface QnaService extends IService<Qna> {
    Page<Qna> page(PageDTO pageDTO);

    List<Qna> listAll();

    Qna getQnaById(String id);

    boolean deleteQnaById(String id);

    boolean addQna(Qna obj);

    boolean updateQnaById(Qna obj);
}
