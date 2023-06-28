package com.aspire.lms.controller;

import com.aspire.lms.dto.LoanApproveDTO;
import com.aspire.lms.dto.LoanDTO;
import com.aspire.lms.dto.RepaymentDTO;
import com.aspire.lms.exception.IllegalStateException;
import com.aspire.lms.exception.InsufficientPrivilegesException;
import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.model.Loan;
import com.aspire.lms.model.User;
import com.aspire.lms.model.enums.UserRole;
import com.aspire.lms.services.LoanService;
import com.aspire.lms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

    @PostMapping("")
    public Loan createLoan(@RequestBody LoanDTO loan) {
        return loanService.save(loan);
    }

    @PutMapping("/{id}/approve")
    public boolean approveLoan(
            @PathVariable(value = "id") Long loanId,
            @RequestBody LoanApproveDTO loanApproveDTO)
    throws EntityNotFoundException {
        // Check to ensure that only users with admin role can approve a loan
        Long userId = loanApproveDTO.getUserId();
        User user = userService.getCustomerById(userId);
        if(!user.getRole().equals(UserRole.ADMIN)) {
            throw new EntityNotFoundException("Loan approval requires admin role");
        }

        return loanService.approveLoan(loanId);
    }

    @GetMapping("/{id}")
    public Loan getLoanById(
            @PathVariable(value = "id") Long loanId,
            @RequestParam("userId") Long requestingUserId
            )
            throws EntityNotFoundException, InsufficientPrivilegesException {
        // Policy check to ensure that a customer can only view their own loans
        Loan loan = loanService.getLoan(loanId);
        if(loan.getCustomer().getId().equals(requestingUserId)) {
            return loanService.getLoan(loanId);
        } else {
            throw new InsufficientPrivilegesException("Loan can only be viewed by the loan taker");
        }
    }

    @PostMapping("{id}/repayments")
    public boolean addLoanRepayment(@PathVariable(value = "id") Long loanId,
                                    @RequestBody RepaymentDTO repayment)
            throws EntityNotFoundException, IllegalStateException {
        loanService.addLoanRepayment(loanId, repayment);
        return true;
    }
}
