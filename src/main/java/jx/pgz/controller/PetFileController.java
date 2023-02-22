package jx.pgz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import jx.pgz.dao.yw.entity.PetFile;
import jx.pgz.dao.yw.service.PetFileService;
import jx.pgz.model.dto.PageCwdaDTO;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/petFile")
@RestController
@Api(tags = "宠物档案")
@RequiredArgsConstructor
public class PetFileController {

    private final PetFileService petFileService;

    @PostMapping("/page")
    public Result<Page<PetFile>> page(@RequestBody PageCwdaDTO pageDTO) {
        Page<PetFile> page = petFileService
                .lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getName()),PetFile::getXm,pageDTO.getName())
                .eq(PetFile::getCreateBy, UserContext.getInstance().getUserId())
                .page(pageDTO.getMybatisPage());
        return Result.ok(page);
    }


    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return Result.ok(petFileService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }


    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody PetFile petFile) {
        petFile.setNl((float) (petFile.getN()*12+petFile.getY()));
        return Result.ok(petFileService.saveOrUpdate(petFile)).setShowMsg(true).setMsg("操作成功");
    }


    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody PetFile petFile) {
        return Result.ok(petFileService.updateById(petFile)).setShowMsg(true).setMsg("修改成功");
    }

    @GetMapping("/getById/{id}")
    public Result<PetFile> getById(@PathVariable("id") Long id) {
        return Result.ok(petFileService.getById(id));
    }
}
