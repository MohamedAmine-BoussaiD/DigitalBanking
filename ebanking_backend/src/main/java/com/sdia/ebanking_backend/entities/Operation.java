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
    private OperationType Type ;

    @ManyToOne
    private BankAccount bankAccount ;
}
