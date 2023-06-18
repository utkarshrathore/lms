package com.aspire.lms.model;

import com.aspire.lms.model.enums.RepaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan.id")
    private Loan loan;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date repaymentDate;

    private Float repaymentAmount;

    private String paymentTransactionId;

    private RepaymentStatus status;
}
