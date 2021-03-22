package com.leverx.nvasilyeva.pet.repository;

import com.leverx.nvasilyeva.pet.entity.Cat;
import com.leverx.nvasilyeva.pet.entity.Pet;
import com.leverx.nvasilyeva.pet.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findAllByColor(String color);
}
