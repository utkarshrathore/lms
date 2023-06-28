package com.aspire.lms.services;

import com.aspire.lms.dto.LoanDTO;
import com.aspire.lms.dto.RepaymentDTO;
import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.exception.IllegalStateException;
import com.aspire.lms.model.Loan;

public interface LoanService {
    Loan save(LoanDTO loanDTO);
    boolean approveLoan(Long loanId);
    Loan getLoan(Long loanId) throws EntityNotFoundException;

    void addLoanRepayment(Long loanId, RepaymentDTO repaymentDTO) throws EntityNotFoundException, IllegalStateException;

}
