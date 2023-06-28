package com.aspire.lms.services.impl;

import com.aspire.lms.dto.LoanDTO;
import com.aspire.lms.dto.RepaymentDTO;
import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.exception.IllegalStateException;
import com.aspire.lms.mappers.LoanMapper;
import com.aspire.lms.mappers.RepaymentMapper;
import com.aspire.lms.model.Loan;
import com.aspire.lms.model.Repayment;
import com.aspire.lms.model.enums.LoanStatus;
import com.aspire.lms.model.enums.RepaymentStatus;
import com.aspire.lms.repository.LoanRepository;
import com.aspire.lms.repository.RepaymentRepository;
import com.aspire.lms.services.LoanService;
import com.aspire.lms.services.RepaymentService;
import com.aspire.lms.utils.LoanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private RepaymentMapper repaymentMapper;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private RepaymentRepository repaymentRepository;

    @Autowired
    private RepaymentService repaymentService;

    @Override
    public Loan save(LoanDTO loanCreationDto) {
        Loan loan = loanMapper.loanDTOtoLoan(loanCreationDto);
        loan.setStatus(LoanStatus.PENDING);
        loanRepository.save(loan);

        List<RepaymentDTO> repayments = LoanUtils.getRepaymentScheduleForLoan(loanCreationDto);
        for(RepaymentDTO repaymentDTO: repayments) {
            Repayment repayment = repaymentMapper.repaymentDTOtoRepayment(repaymentDTO);
            repayment.setLoan(loan);
            repayment.setCurrency(loan.getCurrency());
            repayment.setStatus(RepaymentStatus.PENDING);
        }

        return loan;
    }

    @Override
    public boolean approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.setStatus(LoanStatus.APPROVED);
        loanRepository.save(loan);
        return true;
    }

    @Override
    public Loan getLoan(Long loanId) throws EntityNotFoundException {
        return loanRepository.findById(loanId).orElseThrow(
                ()-> new EntityNotFoundException("Loan with id: "+ loanId+ " not found"));
    }

    @Override
    public void addLoanRepayment(Long loanId, RepaymentDTO repayment) throws EntityNotFoundException, IllegalStateException {
        Loan loan = getLoan(loanId);
        assert (loan != null);
        List<Repayment> repayments = repaymentService.findAllRepaymentByLoan(loanId);
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
        repaymentService.save(repaymentDue);

        /*
         ** Mark the loan as paid if no repayments are pending i.e.
         *  if we had only one repayment pending which was paid successfully
         */
        if(repayments.size() == 1) {
            loan.setStatus(LoanStatus.PAID);
            loanRepository.save(loan);
        }
    }
}