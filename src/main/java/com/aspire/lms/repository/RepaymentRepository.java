package com.aspire.lms.repository;

import com.aspire.lms.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findAllByLoanIdOrderByRepaymentDateAsc(Long loanId);
}
