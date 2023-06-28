package com.aspire.lms.services;

import com.aspire.lms.model.Repayment;

import java.util.List;

public interface RepaymentService {
    List<Repayment> findAllRepaymentByLoan(Long loanId);
    Repayment save(Repayment repayment);

}
