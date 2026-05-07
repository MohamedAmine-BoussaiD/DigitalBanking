package com.sdia.ebanking_backend.repositories;

import com.sdia.ebanking_backend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
