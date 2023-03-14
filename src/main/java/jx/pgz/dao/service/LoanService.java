package jx.pgz.dao.service;

import jx.pgz.dao.entity.Loan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import jx.pgz.model.dto.PageDTO;
/**
 * <p>
 * 贷款信息表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface LoanService extends IService<Loan> {
        Page<Loan> page(PageDTO pageDTO);
        List<Loan> listAll();
        Loan getLoanById(String id);
        boolean deleteLoanById(String id);
        boolean addLoan(Loan obj);
        boolean applyForLoan(Loan obj);
        boolean updateLoanById(Loan obj);

        Double calculateInterest(Loan obj);

        Object repayment(Loan obj);

        Object financialReview(Loan obj);

        Object informationReview(Loan obj);

        Object statistics();
}
