package com.aspire.lms.utils;

import com.aspire.lms.dto.LoanDTO;
import com.aspire.lms.dto.RepaymentDTO;
import com.aspire.lms.model.enums.RepaymentStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanUtils {
    public static List<RepaymentDTO> getRepaymentScheduleForLoan(LoanDTO loanCreationDto) {
        List<RepaymentDTO> repayments = new ArrayList<>();
        float amount = loanCreationDto.getAmount();
        int term = loanCreationDto.getTerm();
        LocalDate emiDate = loanCreationDto.getStartDate();
        float emiAmount = (float) Math.round(((amount/term) * 100.0) / 100.0);
        float totalEmiAmount = 0f;
        while(term-- > 0) {
            emiDate = emiDate.plusWeeks(1);
            emiAmount = (term == 1)? (amount - totalEmiAmount): emiAmount;
            totalEmiAmount += emiAmount;
            repayments.add(new RepaymentDTO(emiAmount, loanCreationDto.getCurrency(), emiDate, RepaymentStatus.PENDING ));
        }

        return repayments;
    }
}
