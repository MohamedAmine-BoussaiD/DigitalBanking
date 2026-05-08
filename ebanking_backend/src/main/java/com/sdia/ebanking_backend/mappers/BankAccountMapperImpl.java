package com.sdia.ebanking_backend.mappers;

import com.sdia.ebanking_backend.dtos.CustomerDTO;
import com.sdia.ebanking_backend.entities.Customer;
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
}
