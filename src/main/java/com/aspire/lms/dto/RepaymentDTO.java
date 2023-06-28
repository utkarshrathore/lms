package com.aspire.lms.dto;

import com.aspire.lms.model.enums.CurrencyTypes;
import com.aspire.lms.model.enums.RepaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class RepaymentDTO {
    private float amount;

    private CurrencyTypes currency;

    private LocalDate date;

    private RepaymentStatus status;
}
