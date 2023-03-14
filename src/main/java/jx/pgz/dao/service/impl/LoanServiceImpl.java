package jx.pgz.dao.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jx.pgz.dao.entity.Loan;
import jx.pgz.dao.entity.SysUser;
import jx.pgz.dao.mapper.LoanMapper;
import jx.pgz.dao.service.LoanService;
import jx.pgz.dao.service.SysUserService;
import jx.pgz.enums.RoleTypeEnum;
import jx.pgz.model.StatisticsVO;
import jx.pgz.model.dto.PageDTO;
import jx.pgz.utils.UserContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 贷款信息表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class LoanServiceImpl extends ServiceImpl<LoanMapper, Loan> implements LoanService {

    @Resource
    private SysUserService sysUserservice;

    @Override
    public Page<Loan> page(PageDTO pageDTO) {

        Page<Loan> page;


        if (UserContext.getRole().contains(RoleTypeEnum.XS.getType())) {
            //信息审核人员数据
            page = lambdaQuery().isNull(Loan::getXdSh).page(pageDTO.getMybatisPage());

        } else if (UserContext.getRole().contains(RoleTypeEnum.CW.getType())) {
            //财务人员数据
            page = lambdaQuery()
                    .eq(Loan::getXdSh, "1")
                    .isNull(Loan::getCwSh)
                    .page(pageDTO.getMybatisPage());
        } else if (UserContext.getRole().contains(RoleTypeEnum.KF.getType())) {
            //客户人员欠款催收数据
            page = lambdaQuery()
                    .eq(Loan::getPostpone, "1")
                    .isNull(Loan::getCwSh)
                    .page(pageDTO.getMybatisPage());
        } else if (UserContext.getRole().contains(RoleTypeEnum.GL.getType())) {
            //客户人员欠款催收数据
            page = lambdaQuery()
                    .page(pageDTO.getMybatisPage());
        } else {
            page = lambdaQuery()
                    .eq(Loan::getUserId, UserContext.getUser().getId())
                    .page(pageDTO.getMybatisPage());
        }


        Set<String> userID = page.getRecords().stream().map(Loan::getUserId).collect(Collectors.toSet());
        if (!userID.isEmpty()) {
            Map<String, SysUser> userMap = sysUserservice.lambdaQuery().in(SysUser::getId, userID).list().stream().collect(Collectors.toMap(SysUser::getId, u -> u));
            for (Loan record : page.getRecords()) {
                if (userMap.containsKey(record.getUserId())) {
                    SysUser sysUser = userMap.get(record.getUserId());
                    sysUser.setPassword(null);
                    record.setSysUser(sysUser);
                }
            }
        }


        //逾期处理
        for (Loan record : page.getRecords()) {
            LocalDate repaymentDate = record.getRepaymentDate();
            if (repaymentDate.isBefore(LocalDate.now())) {
                record.setPostpone("1");
                updateById(record);
            }
        }

        return page;
    }

    @Override
    public List<Loan> listAll() {
        return list();
    }

    @Override
    public Loan getLoanById(String id) {
        return getById(id);
    }

    @Override
    public boolean deleteLoanById(String id) {
        return removeById(id);
    }

    @Override
    public boolean addLoan(Loan obj) {
        return save(obj);
    }

    @Override
    public boolean applyForLoan(Loan obj) {
        obj.setUserId(UserContext.getUser().getId());
        return save(obj);
    }


    @Override
    public boolean updateLoanById(Loan obj) {
        return updateById(obj);
    }

    @Override
    public Double calculateInterest(Loan obj) {

        //归还日期
        LocalDate repaymentDate = obj.getRepaymentDate();
        //贷款金额
        Double amount = obj.getAmount();
        if (amount != null && repaymentDate != null) {
            //天数
            long day = Duration.between(LocalDateTime.now(), LocalDateTime.of(repaymentDate, LocalTime.now())).toDays();
            //日利率0.03%
            return Double.parseDouble(String.format("%.2f", (amount * 0.03 / 100 * day)));
        }

        return 0D;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Object repayment(Loan obj) {
        if (obj.getRet() != null) {
            Loan loan = getById(obj.getId());
            Double amountPaid = loan.getAmountPaid();
            if (amountPaid != null) {
                loan.setAmountPaid(amountPaid + obj.getRet());
            } else {
                loan.setAmountPaid(obj.getRet());
            }
            //判断还款金额是否大于等于贷款金额加利息
            if (loan.getAmountPaid() >= loan.getAmount() + loan.getInterest()) {
                //设置还款状态
                loan.setHkZt("0");
            }
            return updateById(loan);
        }
        return false;
    }


    @Override
    public Object financialReview(Loan obj) {
        Loan loan = getById(obj.getId());
        loan.setCwSh(obj.getCwSh());
        return updateById(loan);
    }

    @Override
    public Object informationReview(Loan obj) {
        Loan loan = getById(obj.getId());
        loan.setXdSh(obj.getXdSh());
        return updateById(loan);
    }

    @Override
    public Object statistics() {

        Map<String, Object> map = new HashMap<>();

        List<Loan> list = list();

        map.put("dkrs", list.size());
        map.put("yqrs", list.stream().filter(s -> "1".equals(s.getPostpone())).count());
        map.put("dkze", list.stream().mapToDouble(Loan::getAmount).sum());
        map.put("lxze", list.stream().mapToDouble(Loan::getInterest).sum());

//        { value: 1048, name: '早上' },
        // { value: 1048, name: '0-1000元' },

        Map<String, Integer> sjfb = new HashMap<>();
        String sw = "上午";
        String xw = "下午";
        String yj = "夜间";
        String lc = "凌晨";
        sjfb.put(sw, 0);
        sjfb.put(xw, 0);
        sjfb.put(yj, 0);
        sjfb.put(lc, 0);


        Map<String, Integer> dkfb = new HashMap<>();
        String qj1 = "0-1000元";
        String qj2 = "1001-5000元";
        String qj3 = "5000-1000元";
        String qj4 = "10001元以上";
        dkfb.put(qj1, 0);
        dkfb.put(qj2, 0);
        dkfb.put(qj3, 0);
        dkfb.put(qj4, 0);


        List<StatisticsVO> sjfbList = new ArrayList<>();
        List<StatisticsVO> dkfbList = new ArrayList<>();
        for (Loan loan : list) {
            LocalTime createTime = loan.getCreateTime().toLocalTime();
            //凌晨0-5时 59分 59秒点
            if (createTime.isAfter(LocalTime.of(0, 0, 0)) && createTime.isBefore(LocalTime.of(5, 59, 59))) {
                sjfb.put(lc, sjfb.get(lc) + 1);
            }
            //上午6-11时 59分 59秒点
            if (createTime.isAfter(LocalTime.of(6, 0, 0)) && createTime.isBefore(LocalTime.of(11, 59, 59))) {
                sjfb.put(sw, sjfb.get(sw) + 1);
            }
            //下午12-17时 59分 59秒点
            if (createTime.isAfter(LocalTime.of(12, 0, 0)) && createTime.isBefore(LocalTime.of(17, 59, 59))) {
                sjfb.put(xw, sjfb.get(xw) + 1);
            }
            //夜间18-23时 59分 59秒点
            if (createTime.isAfter(LocalTime.of(18, 0, 0)) && createTime.isBefore(LocalTime.of(23, 59, 59))) {
                sjfb.put(yj, sjfb.get(yj) + 1);
            }

            Double amount = loan.getAmount();
            if (amount > 0 && amount <= 1000) {
                dkfb.put(qj1, dkfb.get(qj1) + 1);
            } else if (amount > 1000 && amount <= 5000) {
                dkfb.put(qj2, dkfb.get(qj2) + 1);
            } else if (amount > 5000 && amount <= 10000) {
                dkfb.put(qj3, dkfb.get(qj3) + 1);
            } else {
                dkfb.put(qj4, dkfb.get(qj4) + 1);
            }

        }

        sjfb.forEach((k, v) -> {
            StatisticsVO vo = new StatisticsVO();
            vo.setName(k);
            vo.setValue(v);
            sjfbList.add(vo);
        });

        dkfb.forEach((k, v) -> {
            StatisticsVO vo = new StatisticsVO();
            vo.setName(k);
            vo.setValue(v);
            dkfbList.add(vo);
        });

        map.put("dksjfb", sjfbList);
        map.put("dkqjfb", dkfbList);

        return map;
    }
}
