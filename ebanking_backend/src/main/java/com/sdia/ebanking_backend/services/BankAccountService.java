package com.sdia.ebanking_backend.services;

import com.sdia.ebanking_backend.dtos.*;
import com.sdia.ebanking_backend.entities.BankAccount;
import com.sdia.ebanking_backend.entities.CurrentAccount;
import com.sdia.ebanking_backend.entities.Customer;
import com.sdia.ebanking_backend.entities.SavingAccount;
import com.sdia.ebanking_backend.exceptions.BalanceNotSufficientException;
import com.sdia.ebanking_backend.exceptions.BankAccountNotFoundException;
import com.sdia.ebanking_backend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance , double OverDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance , double interestRate , Long custoemrId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount , String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId ,  double amount , String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource , String accountIdDestination , double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();
    CustomerDTO getCustomer(Long id) throws CustomerNotFoundException;
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long id);

    List<OperationDTO> accountHistory(String accountId);
}
