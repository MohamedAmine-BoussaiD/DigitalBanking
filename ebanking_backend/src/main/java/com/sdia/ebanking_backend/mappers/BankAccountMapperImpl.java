package com.sdia.ebanking_backend.mappers;

import com.sdia.ebanking_backend.dtos.CurrentBankAccountDTO;
import com.sdia.ebanking_backend.dtos.CustomerDTO;
import com.sdia.ebanking_backend.dtos.OperationDTO;
import com.sdia.ebanking_backend.dtos.SavingBankAccountDTO;
import com.sdia.ebanking_backend.entities.CurrentAccount;
import com.sdia.ebanking_backend.entities.Customer;
import com.sdia.ebanking_backend.entities.Operation;
import com.sdia.ebanking_backend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

// MapStruct

@Service
public class BankAccountMapperImpl {

    public CustomerDTO fromCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer ;
    }

    public CurrentAccount fromCurrentAccountDTOTOCurrentAccount(CurrentBankAccountDTO currentAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO , currentAccount);
        currentAccount.setCustomer(fromCustomerDTOToCustomer(currentAccountDTO.getCustomerDTO()));
        return currentAccount ;
    }

    public CurrentBankAccountDTO  fromCurrentAccountTOCurrentAccountDTO(CurrentAccount currentAccount){
        CurrentBankAccountDTO currenAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount,currenAccountDTO);
        currenAccountDTO.setCustomerDTO(fromCustomerToCustomerDTO(currentAccount.getCustomer()));
        currenAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currenAccountDTO;
    }

    public SavingBankAccountDTO fromSavingAccountToSavingAccountDTO(SavingAccount savingAccount){
        SavingBankAccountDTO savingAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount , savingAccountDTO);
        savingAccountDTO.setCustomerDTO(fromCustomerToCustomerDTO(savingAccount.getCustomer()));
        savingAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingAccountDTO;
    }

    public SavingAccount fromSavingAccountDTOToSavingAccount(SavingBankAccountDTO savingAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO , savingAccount);
        savingAccount.setCustomer(fromCustomerDTOToCustomer(savingAccountDTO.getCustomerDTO()));
        return savingAccount ;
    }

    public OperationDTO fromOperationToOperationDTO(Operation operation){
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(operation , operationDTO);
        return operationDTO;
    }

    public Operation fromOperationDTOToOperation(OperationDTO operationDTO){
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDTO , operation);
        return operation;
    }
}
