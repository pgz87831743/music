package jx.pgz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import jx.pgz.dao.entity.Loan;
import jx.pgz.dao.service.LoanService;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 贷款信息表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService iLoanService;


    @PostMapping("page")
    @ApiOperation("贷款信息分页")
    public Result<Page<Loan>> page(@RequestBody PageDTO pageDTO) {
        return Result.ok(iLoanService.page(pageDTO));
    }

    @GetMapping("listAll")
    @ApiOperation("贷款信息列表")
    public Result<List<Loan>> listAll() {
        return Result.ok(iLoanService.listAll());
    }

    @GetMapping("getById/{id}")
    public Result<Loan> getLoanById(@PathVariable("id") String id) {
        return Result.ok(iLoanService.getLoanById(id));
    }

    @DeleteMapping("deleteById/{id}")
    public Result<Boolean> deleteLoanById(@PathVariable("id") String id) {
        return Result.ok(iLoanService.deleteLoanById(id));
    }


    @ApiOperation("申请贷款")
    @PostMapping("applyForLoan")
    public Result<Boolean> applyForLoan(@RequestBody Loan obj) {
        return Result.ok(iLoanService.applyForLoan(obj));
    }

    @ApiOperation("计算贷款利息")
    @PostMapping("calculateInterest")
    public Result<Double> calculateInterest(@RequestBody Loan obj) {
        return Result.ok(iLoanService.calculateInterest(obj));
    }


    @ApiOperation("还款")
    @PostMapping("repayment")
    public Result<Object> repayment(@RequestBody Loan obj) {
        return Result.ok(iLoanService.repayment(obj)).setMsg("成功还款"+obj.getRet()+"元").setShowMsg(true);
    }

    @ApiOperation("财务审核")
    @PostMapping("financialReview")
    public Result<Object> financialReview(@RequestBody Loan obj) {
        return Result.ok(iLoanService.financialReview(obj)).setMsg("审核完成").setShowMsg(true);
    }

    @ApiOperation("信息审核")
    @PostMapping("informationReview")
    public Result<Object> informationReview(@RequestBody Loan obj) {
        return Result.ok(iLoanService.informationReview(obj)).setMsg("审核完成").setShowMsg(true);
    }


    @ApiOperation("系统参数统计")
    @PostMapping("statistics")
    public Result<Object> statistics() {
        return Result.ok(iLoanService.statistics());
    }



    @PostMapping("add")
    public Result<Boolean> addLoan(@RequestBody Loan obj) {
        return Result.ok(iLoanService.addLoan(obj));
    }


    @PutMapping("updateById")
    public Result<Boolean> updateLoanById(@RequestBody Loan obj) {
        return Result.ok(iLoanService.updateLoanById(obj));
    }

}
