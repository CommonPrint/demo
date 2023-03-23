package com.demo.entity.payment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Если тип оплаты будет "cash", то cardNetwork и cardDetails будут пустыми
    private String cardDetails;

    private String cardNetwork;

    private Instant paymentTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

}
