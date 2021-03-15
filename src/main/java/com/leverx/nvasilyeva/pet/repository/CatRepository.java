package com.leverx.nvasilyeva.pet.repository;

import com.leverx.nvasilyeva.pet.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatRepository extends JpaRepository<Cat, Long> {

    Cat findAllByColor(String color);
    Cat findAllByOwnerIdAndColor(String ownerId, String color);

}
