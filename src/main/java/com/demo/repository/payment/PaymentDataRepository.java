package com.demo.repository.payment;

import com.demo.entity.payment.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDataRepository extends JpaRepository<PaymentData, Long> {

}
