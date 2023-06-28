package com.aspire.lms.mappers;

import com.aspire.lms.dto.LoanDTO;
import com.aspire.lms.dto.RepaymentDTO;
import com.aspire.lms.model.Loan;
import com.aspire.lms.model.Repayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RepaymentMapper {
    @Mapping(target="amount", source="repayment.repaymentAmount")
    @Mapping(target="date", source="repayment.repaymentDate")
    RepaymentDTO repaymentToRepaymentDTO(Repayment repayment);

    @Mapping(target="repaymentAmount", source="repaymentDTO.amount")
    @Mapping(target="repaymentDate", source="repaymentDTO.date")
    Repayment repaymentDTOtoRepayment(RepaymentDTO repaymentDTO);
}
