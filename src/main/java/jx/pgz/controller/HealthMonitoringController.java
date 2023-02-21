package jx.pgz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import jx.pgz.dao.yw.entity.HealthMonitoring;
import jx.pgz.dao.yw.service.HealthMonitoringService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import jx.pgz.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/healthMonitoring")
@RestController
@Api(tags = "宠物饲养技巧")
@RequiredArgsConstructor
public class HealthMonitoringController {

    private final HealthMonitoringService healthMonitoringService;

    @PostMapping("/page")
    public Result<Page<HealthMonitoring>> page(@RequestBody PageDTO pageDTO) {
        Page<HealthMonitoring> page = healthMonitoringService
                .lambdaQuery()
                .eq(HealthMonitoring::getCreateBy, UserContext.getInstance().getUserId())
                .page(pageDTO.getMybatisPage());
        return Result.ok(page);
    }


    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return Result.ok(healthMonitoringService.removeById(id)).setShowMsg(true).setMsg("删除成功");
    }


}
