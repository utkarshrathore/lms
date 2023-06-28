package com.aspire.lms.services.impl;

import com.aspire.lms.model.Repayment;
import com.aspire.lms.repository.RepaymentRepository;
import com.aspire.lms.services.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RepaymentServiceImpl implements RepaymentService {
    @Autowired
    private RepaymentRepository repaymentRepository;
    @Override
    public List<Repayment> findAllRepaymentByLoan(Long loanId) {
        return repaymentRepository.findAllByLoanIdOrderByRepaymentDateAsc(loanId);
    }

    @Override
    public Repayment save(Repayment repayment) {
        return repaymentRepository.save(repayment);
    }
}
