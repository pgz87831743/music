package jx.pgz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import jx.pgz.dao.yw.entity.DailyRecord;
import jx.pgz.dao.yw.entity.PetFile;
import jx.pgz.dao.yw.service.DailyRecordService;
import jx.pgz.dao.yw.service.PetFileService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dailyRecord")
@RestController
@Api(tags = "每日记录")
@RequiredArgsConstructor
public class DailyRecordController {

    private final DailyRecordService dailyRecordService;
    private final PetFileService petFileService;

    @PostMapping("/page")
    public Result<Page<DailyRecord>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(dailyRecordService.page(pageDTO));
    }


    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return Result.ok(dailyRecordService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }


    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody DailyRecord dailyRecord) {
        return Result.ok(dailyRecordService.saveDailyRecord(dailyRecord)).setShowMsg(true).setMsg("操作成功");
    }


    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody DailyRecord dailyRecord) {
        return Result.ok(dailyRecordService.updateById(dailyRecord)).setShowMsg(true).setMsg("修改成功");
    }

    @GetMapping("/getById/{id}")
    public Result<DailyRecord> getById(@PathVariable("id") Long id) {
        return Result.ok(dailyRecordService.getById(id));
    }

    @GetMapping("/allPet")
    public Result<List<PetFile>> allPet() {
        return Result.ok(petFileService.lambdaQuery().eq(PetFile::getCreateBy,UserContext.getInstance().getUserId()).list());
    }
}
