package com.sdia.ebanking_backend.repositories;

import com.sdia.ebanking_backend.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {
}
