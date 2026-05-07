package com.sdia.ebanking_backend.entities;
import com.sdia.ebanking_backend.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType = DiscriminatorType.STRING)

@Data @AllArgsConstructor @NoArgsConstructor
public class BankAccount {

    @Id
    private String id ;

    private Date createdAt ;
    private double balance ;

    @Enumerated(EnumType.STRING)
    private AccountStatus status ;

//    private String currency ;

    @ManyToOne
    private Customer customer ;

    @OneToMany(mappedBy="bankAccount")
    private List<Operation> operations ;
}
