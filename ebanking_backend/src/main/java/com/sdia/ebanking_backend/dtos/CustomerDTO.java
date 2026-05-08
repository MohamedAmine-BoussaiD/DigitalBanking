package com.sdia.ebanking_backend.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdia.ebanking_backend.entities.BankAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class CustomerDTO {
    private Long id ;
    private String name ;
    private String email ;
}
