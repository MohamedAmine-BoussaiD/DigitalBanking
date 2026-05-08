package com.sdia.ebanking_backend.entities;

import com.sdia.ebanking_backend.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date operationDate ;
    private double amount ;

    @Enumerated(EnumType.STRING)
    private OperationType Type ;

    private String description ;

    @ManyToOne
    private BankAccount bankAccount ;
}
