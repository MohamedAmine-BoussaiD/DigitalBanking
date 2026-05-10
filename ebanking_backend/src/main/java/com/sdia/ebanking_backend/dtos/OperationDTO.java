package com.sdia.ebanking_backend.dtos;

import com.sdia.ebanking_backend.entities.BankAccount;
import com.sdia.ebanking_backend.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
public class OperationDTO {

    private Long id ;
    private Date operationDate ;
    private double amount ;
    private OperationType Type ;
    private String description ;
}
