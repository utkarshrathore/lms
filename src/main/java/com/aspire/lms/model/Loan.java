package com.aspire.lms.model;

import com.aspire.lms.model.enums.CurrencyTypes;
import com.aspire.lms.model.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user.id")
    private User user;

    private float amount;

    private CurrencyTypes currency;

    private int term;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
}