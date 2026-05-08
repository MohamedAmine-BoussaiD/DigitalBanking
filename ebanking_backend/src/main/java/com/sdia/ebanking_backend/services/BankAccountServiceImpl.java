package com.sdia.ebanking_backend.services;

import com.sdia.ebanking_backend.dtos.CustomerDTO;
import com.sdia.ebanking_backend.entities.*;
import com.sdia.ebanking_backend.enums.OperationType;
import com.sdia.ebanking_backend.exceptions.BalanceNotSufficientException;
import com.sdia.ebanking_backend.exceptions.BankAccountNotFoundException;
import com.sdia.ebanking_backend.exceptions.CustomerNotFoundException;
import com.sdia.ebanking_backend.mappers.BankAccountMapperImpl;
import com.sdia.ebanking_backend.repositories.BankAccountRepository;
import com.sdia.ebanking_backend.repositories.CustomerRepository;
import com.sdia.ebanking_backend.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j


public class BankAccountServiceImpl implements BankAccountService {

    private CustomerRepository  customerRepository;
    private BankAccountRepository bankAccountRepository;
    private OperationRepository operationRepository;
    private BankAccountMapperImpl dtoMapper;

    // journalization
    // en peut le remplacer par  @Slf4j
    //Logger log = LoggerFactory.getLogger(this.getClass().getName());

/*
    public BankAccountServiceImpl(CustomerRepository  customerRepository,BankAccountRepository bankAccountRepository, OperationRepository operationRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.operationRepository = operationRepository;
    }
*/

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }

        CurrentAccount bankAccount = new CurrentAccount() ;
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setCustomer(customer);
        bankAccount.setOverdraft(overDraft);

        CurrentAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return savedBankAccount;
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }

        SavingAccount bankAccount = new SavingAccount() ;
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setCustomer(customer);
        bankAccount.setInterestRate(interestRate);

        SavingAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return savedBankAccount;
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();

        // programation fonctionnel
        List<CustomerDTO> customerDTOS = customers.stream()
                        .map(customer -> dtoMapper.fromCustomerToCustomerDTO(customer))
                        .collect(Collectors.toList());
        return customerDTOS ;

         //programation Imperative
/*
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for(Customer customer : customers){
            CustomerDTO customerDTO = dtoMapper.fromCustomerToCustomerDTO(customer);
            customerDTOs.add(customerDTO);
        }
*/
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException  {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                    .orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
        return bankAccount;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = getBankAccount(accountId);
        if(bankAccount.getBalance() < amount) {
            throw new BalanceNotSufficientException("Balance Not Sufficient");
        }
        Operation operation = new Operation();
        operation.setType(OperationType.DEBIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setBankAccount(bankAccount);
        operationRepository.save(operation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }


    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = getBankAccount(accountId);
        Operation operation = new Operation();
        operation.setType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setBankAccount(bankAccount);
        operationRepository.save(operation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource , amount , "Transfer to "+accountIdDestination);
        credit(accountIdDestination , amount , "Transfer from "+accountIdSource);
    }

    @Override
    public List<BankAccount> bankAccountList(){
        return bankAccountRepository.findAll();
    }

    @Override
    public  CustomerDTO getCustomer(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer Not Found"));
        return dtoMapper.fromCustomerToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO){
        Customer customer = dtoMapper.fromCustomerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomerToCustomerDTO(savedCustomer) ;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO){
        Customer customer = dtoMapper.fromCustomerDTOToCustomer(customerDTO);
        Customer updatedCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomerToCustomerDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
}
