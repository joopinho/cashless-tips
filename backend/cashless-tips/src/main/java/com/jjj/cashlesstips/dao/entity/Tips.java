package com.jjj.cashlesstips.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "tips", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class Tips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    BigDecimal amount;

   // Integer status;

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "created_dt")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "external_id")
    private String externalId;

    public Tips (BigDecimal amount, Long orderId, String externalId){
        this.amount = amount;
        this.orderId = orderId;
        this.externalId = externalId;
    }
    
}
