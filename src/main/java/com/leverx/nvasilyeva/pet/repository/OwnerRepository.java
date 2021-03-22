package com.leverx.nvasilyeva.pet.repository;

import com.leverx.nvasilyeva.pet.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findOwnerByEmail(String email);
    boolean existsOwnerByEmail(String email);
}
