package com.sdia.ebanking_backend.web;


import com.sdia.ebanking_backend.dtos.BankAccountDTO;
import com.sdia.ebanking_backend.dtos.OperationDTO;
import com.sdia.ebanking_backend.exceptions.BankAccountNotFoundException;
import com.sdia.ebanking_backend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestController {

    BankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
       BankAccountDTO bankAccountDTO =  bankAccountService.getBankAccount(accountId);
       return bankAccountDTO;
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts() throws BankAccountNotFoundException {
        List<BankAccountDTO> bankAccountDTOList = bankAccountService.bankAccountList();
        return bankAccountDTOList;
    }

    @GetMapping("/accounts/{id}/operations")
    public List<OperationDTO> getHistory(@PathVariable String id){
        List<OperationDTO> operationHistory =bankAccountService.accountHistory(id);
        return operationHistory;
    }


}
