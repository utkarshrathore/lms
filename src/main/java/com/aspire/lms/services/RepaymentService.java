package com.aspire.lms.services;

import com.aspire.lms.dto.RepaymentDTO;
import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.exception.IllegalStateException;
import com.aspire.lms.model.Repayment;

import java.util.List;

public interface RepaymentService {
    List<Repayment> findAllRepaymentByLoan(Long loanId);
    Repayment save(Repayment repayment);

    public void addLoanRepayment(Long loanId, RepaymentDTO repayment) throws EntityNotFoundException, IllegalStateException;

}
