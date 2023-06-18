package com.aspire.lms.model;

import com.aspire.lms.model.enums.CurrencyTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer.id")
    private Customer customer;

    private Float amount;

    private CurrencyTypes currency;

    private Integer term;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
}