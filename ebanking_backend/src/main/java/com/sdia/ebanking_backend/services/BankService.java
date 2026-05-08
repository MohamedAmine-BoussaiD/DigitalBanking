package com.sdia.ebanking_backend.services;

import com.sdia.ebanking_backend.entities.BankAccount;
import com.sdia.ebanking_backend.entities.CurrentAccount;
import com.sdia.ebanking_backend.entities.SavingAccount;
import com.sdia.ebanking_backend.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository ;


    public void consulter(){
        BankAccount bankAccount =
                bankAccountRepository.findById("d173c4f6-0992-4375-aafe-db62662236ec")
                        .orElse(null);

        System.out.println("********************");
        System.out.println(bankAccount.getId());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getCreatedAt());
        System.out.println(bankAccount.getStatus());
        System.out.println(bankAccount.getCustomer().getName());
        System.out.println(bankAccount.getClass());

        if (bankAccount instanceof SavingAccount) {
            System.out.println("Saving | Rate => "+((SavingAccount)bankAccount).getInterestRate());
        }
        else {
            System.out.println("Current | OverDraft => "+((CurrentAccount)bankAccount).getOverdraft());
        }

        bankAccount.getOperations().forEach( op -> {
            System.out.println(op.getType());
            System.out.println(op.getOperationDate());
            System.out.println(op.getAmount());
        });
    }
}
