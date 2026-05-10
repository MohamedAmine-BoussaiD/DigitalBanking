package com.sdia.ebanking_backend.repositories;

import com.sdia.ebanking_backend.dtos.CustomerDTO;
import com.sdia.ebanking_backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("select c from Customer c where c.name like :kw ")
    List<Customer> searchCustomer(@Param("kw") String Keyword);
}
