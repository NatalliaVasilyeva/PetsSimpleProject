package com.leverx.nvasilyeva.pet.repository;

import com.leverx.nvasilyeva.pet.entity.Pet;
import com.leverx.nvasilyeva.pet.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByOrderByOwnerIdAndPetType(long ownerId, PetType petType);

    @Modifying
    @Query(value = "update pets p set owner_id = null where owner_id= :ownerId", nativeQuery = true)
    void deleteReferencesToOwner(@Param("ownerId") Long ownerId);

}
