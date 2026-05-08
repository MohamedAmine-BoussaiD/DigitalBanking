package com.sdia.ebanking_backend.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String email ;

    @OneToMany(mappedBy = "customer")
     /*
     jackson = convertir objet java en json
     cette anotation permet de ignorer la serialisation de cette objet
     best practice c est d utiliser DTO au lieu des entities pour controller les donner

     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     */
    private List<BankAccount> bankAccounts ;
}
