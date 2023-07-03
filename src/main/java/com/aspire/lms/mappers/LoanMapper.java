package com.aspire.lms.mappers;

import com.aspire.lms.dto.LoanDTO;
import com.aspire.lms.model.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    LoanDTO loanToLoanDTO(Loan loan);

    Loan loanDTOtoLoan(LoanDTO loanDTO);

}
