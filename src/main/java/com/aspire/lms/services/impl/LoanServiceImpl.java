package com.aspire.lms.services.impl;

import com.aspire.lms.dto.LoanDTO;
import com.aspire.lms.dto.RepaymentDTO;
import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.mappers.LoanMapper;
import com.aspire.lms.mappers.RepaymentMapper;
import com.aspire.lms.model.Loan;
import com.aspire.lms.model.Repayment;
import com.aspire.lms.model.User;
import com.aspire.lms.model.enums.LoanStatus;
import com.aspire.lms.model.enums.RepaymentStatus;
import com.aspire.lms.repository.LoanRepository;
import com.aspire.lms.repository.RepaymentRepository;
import com.aspire.lms.services.LoanService;
import com.aspire.lms.services.RepaymentService;
import com.aspire.lms.services.UserService;
import com.aspire.lms.utils.LoanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private RepaymentMapper repaymentMapper;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private RepaymentService repaymentService;

    @Autowired
    private UserService userService;

    @Override
    public Loan save(LoanDTO loanCreationDto) throws EntityNotFoundException {
        Loan loan = loanMapper.loanDTOtoLoan(loanCreationDto);
        User user = userService.getUserById(loanCreationDto.getUserId());
        loan.setUser(user);
        loan.setStatus(LoanStatus.PENDING);
        loanRepository.save(loan);

        List<RepaymentDTO> repayments = LoanUtils.getRepaymentScheduleForLoan(loanCreationDto);
        for(RepaymentDTO repaymentDTO: repayments) {
            Repayment repayment = repaymentMapper.repaymentDTOtoRepayment(repaymentDTO);
            repayment.setLoan(loan);
            repayment.setCurrency(loan.getCurrency());
            repayment.setStatus(RepaymentStatus.PENDING);
            repaymentService.save(repayment);
        }

        return loan;
    }

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
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
}