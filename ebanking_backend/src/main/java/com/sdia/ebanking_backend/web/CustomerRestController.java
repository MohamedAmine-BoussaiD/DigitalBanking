package com.sdia.ebanking_backend.web;

import com.sdia.ebanking_backend.dtos.CustomerDTO;
import com.sdia.ebanking_backend.entities.Customer;
import com.sdia.ebanking_backend.exceptions.CustomerNotFoundException;
import com.sdia.ebanking_backend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {

//    @Autowired
    private BankAccountService bankAccountService;


    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name="id") Long id) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(id);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO)  {
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping ("/customers/{id}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO , @PathVariable Long id){
        customerDTO.setId(id);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }


}
