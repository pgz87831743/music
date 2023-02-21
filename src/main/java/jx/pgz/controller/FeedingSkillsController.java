package jx.pgz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import jx.pgz.dao.yw.entity.FeedingSkills;
import jx.pgz.dao.yw.service.FeedingSkillsService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.model.dto.PageSyjqDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/feedingSkills")
@RestController
@Api(tags = "宠物饲养技巧")
@RequiredArgsConstructor
public class FeedingSkillsController {

    private final FeedingSkillsService feedingSkillsService;

    @PostMapping("/page")
    public Result<Page<FeedingSkills>> page(@RequestBody PageSyjqDTO pageDTO) {
        Page<FeedingSkills> page = feedingSkillsService
                .lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getSearch()),FeedingSkills::getXm,pageDTO.getSearch())
                .or()
                .like(StringUtils.hasText(pageDTO.getSearch()),FeedingSkills::getZl,pageDTO.getSearch())
                .or()
                .like(StringUtils.hasText(pageDTO.getSearch()),FeedingSkills::getXw,pageDTO.getSearch())
                .or()
                .like(StringUtils.hasText(pageDTO.getSearch()),FeedingSkills::getYs,pageDTO.getSearch())
                .or()
                .like(StringUtils.hasText(pageDTO.getSearch()),FeedingSkills::getCwyp,pageDTO.getSearch())
                .page(pageDTO.getMybatisPage());
        return Result.ok(page);
    }


    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return Result.ok(feedingSkillsService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }


    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody FeedingSkills feedingSkills) {
        return Result.ok(feedingSkillsService.saveOrUpdate(feedingSkills)).setShowMsg(true).setMsg("操作成功");
    }


    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody FeedingSkills feedingSkills) {
        return Result.ok(feedingSkillsService.updateById(feedingSkills)).setShowMsg(true).setMsg("修改成功");
    }

    @GetMapping("/getById/{id}")
    public Result<FeedingSkills> getById(@PathVariable("id") Long id) {
        return Result.ok(feedingSkillsService.getById(id));
    }
}
