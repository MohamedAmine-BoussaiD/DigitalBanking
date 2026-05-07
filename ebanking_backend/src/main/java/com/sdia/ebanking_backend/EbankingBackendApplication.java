package com.sdia.ebanking_backend;

import com.sdia.ebanking_backend.entities.*;
import com.sdia.ebanking_backend.enums.AccountStatus;
import com.sdia.ebanking_backend.enums.OperationType;
import com.sdia.ebanking_backend.repositories.BankAccountRepository;
import com.sdia.ebanking_backend.repositories.CustomerRepository;
import com.sdia.ebanking_backend.repositories.OperationRepository;
import org.apache.el.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            CustomerRepository customerRepository ,
            BankAccountRepository bankAccountRepository,
            OperationRepository operationRepository)
    {
        return args -> {
            List<String> names = List.of("amine", "yassine", "rayane");
            names.stream().forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust -> {

                // Current Account
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverdraft(9000);
                bankAccountRepository.save(currentAccount);

                // Saving Account
                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);

            });

            bankAccountRepository.findAll().forEach(acc -> {

                for(int i=0;i<10;i++){
                    Operation operation = new Operation();
                    operation.setAmount(Math.random()*12000);
                    operation.setOperationDate(new Date());
                    operation.setType(Math.random() > 0.5 ?  OperationType.CREDIT : OperationType.DEBIT);
                    operation.setBankAccount(acc);
                    operationRepository.save(operation);
                }

            });

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
                System.out.println(((SavingAccount)bankAccount).getInterestRate());
            }
            else {
                System.out.println(((CurrentAccount)bankAccount).getOverdraft());
            }


        };
    }

}
