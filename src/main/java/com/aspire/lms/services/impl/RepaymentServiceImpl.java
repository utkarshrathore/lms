package com.aspire.lms.services.impl;

import com.aspire.lms.dto.RepaymentDTO;
import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.exception.IllegalStateException;
import com.aspire.lms.model.Loan;
import com.aspire.lms.model.Repayment;
import com.aspire.lms.model.enums.LoanStatus;
import com.aspire.lms.model.enums.RepaymentStatus;
import com.aspire.lms.repository.RepaymentRepository;
import com.aspire.lms.services.LoanService;
import com.aspire.lms.services.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RepaymentServiceImpl implements RepaymentService {
    @Autowired
    private RepaymentRepository repaymentRepository;

    @Lazy
    @Autowired
    private LoanService loanService;
    @Override
    public List<Repayment> findAllRepaymentByLoan(Long loanId) {
        return repaymentRepository.findAllByLoanIdOrderByRepaymentDateAsc(loanId);
    }

    @Override
    public Repayment save(Repayment repayment) {
        return repaymentRepository.save(repayment);
    }

    @Override
    public void addLoanRepayment(Long loanId, RepaymentDTO repayment) throws EntityNotFoundException, IllegalStateException {
        Loan loan = loanService.getLoan(loanId);
        assert (loan != null);
        List<Repayment> repayments = findAllRepaymentByLoan(loanId);
        if(repayments == null || repayments.isEmpty()) {
            throw new EntityNotFoundException("Repayments for oan with id: "+ loanId+ " not found");
        }

        repayments = repayments.stream()
                .filter(repay -> repay.getStatus() != RepaymentStatus.PAID)
                .collect(Collectors.toList());

        Repayment repaymentDue = repayments.get(0);
        if(repayment.getAmount() < repaymentDue.getRepaymentAmount()) {
            throw new IllegalStateException("Repayment amount is less than the registered EMI");
        }

        repaymentDue.setStatus(RepaymentStatus.PAID);
        repaymentDue.setPaymentTransactionId(UUID.randomUUID().toString()); // random uuid fetched by payment processor
        save(repaymentDue);

        /*
         ** Mark the loan as paid if no repayments are pending i.e.
         *  if we had only one repayment pending which was paid successfully
         */
        if(repayments.size() == 1) {
            loan.setStatus(LoanStatus.PAID);
            loanService.saveLoan(loan);
        }
    }
}
