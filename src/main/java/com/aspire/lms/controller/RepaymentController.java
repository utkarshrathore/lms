package com.aspire.lms.controller;

import com.aspire.lms.dto.LoanApproveDTO;
import com.aspire.lms.dto.LoanDTO;
import com.aspire.lms.dto.RepaymentDTO;
import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.exception.IllegalStateException;
import com.aspire.lms.exception.InsufficientPrivilegesException;
import com.aspire.lms.model.Loan;
import com.aspire.lms.model.User;
import com.aspire.lms.model.enums.UserRole;
import com.aspire.lms.services.LoanService;
import com.aspire.lms.services.RepaymentService;
import com.aspire.lms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/repayments")
public class RepaymentController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

    @Autowired
    private RepaymentService repaymentService;

    @PutMapping("{id}/repayments")
    public boolean addLoanRepayment(@PathVariable(value = "id") Long loanId,
                                    @RequestBody RepaymentDTO repayment)
            throws EntityNotFoundException, IllegalStateException {
        repaymentService.addLoanRepayment(loanId, repayment);
        return true;
    }
}

