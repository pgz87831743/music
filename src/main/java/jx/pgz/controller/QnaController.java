package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jx.pgz.dao.entity.Qna;
import jx.pgz.dao.service.QnaService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 问答表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService iQnaService;


    @PostMapping("page")
    public Result<Page<Qna>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(iQnaService.page(pageDTO));
    }

    @GetMapping("listAll")
    public Result<List<Qna>> listAll() {
        return Result.ok(iQnaService.listAll());
    }

    @GetMapping("getById/{id}")
    public Result<Qna> getQnaById(@PathVariable("id") String id) {
        return Result.ok(iQnaService.getQnaById(id));
    }

    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteQnaById(@PathVariable("id") String id) {
        return Result.ok(iQnaService.deleteQnaById(id));
    }

    @PostMapping("add")
    public Result<Boolean> addQna(@RequestBody Qna obj) {
        return Result.ok(iQnaService.addQna(obj));
    }


    @PutMapping("updateById")
    public Result<Boolean> updateQnaById(@RequestBody Qna obj) {
        return Result.ok(iQnaService.updateQnaById(obj));
    }

}
