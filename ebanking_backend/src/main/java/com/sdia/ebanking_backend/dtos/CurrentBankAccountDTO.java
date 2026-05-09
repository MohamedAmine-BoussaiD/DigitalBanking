package com.sdia.ebanking_backend.dtos;

import com.sdia.ebanking_backend.entities.Customer;
import com.sdia.ebanking_backend.enums.AccountStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends  BankAccountDTO{
    private String id ;
    private Date createdAt ;
    private double balance ;
    private AccountStatus status ;
    private CustomerDTO customerDTO ;
    private double overdraft;
}
