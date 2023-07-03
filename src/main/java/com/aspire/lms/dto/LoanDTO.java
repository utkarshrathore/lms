package com.aspire.lms.dto;

import com.aspire.lms.model.enums.CurrencyTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class LoanDTO {
    private float amount;

    private CurrencyTypes currency;

    private int term;

    private Long userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
}
