package com.leverx.nvasilyeva.pet.repository;

import com.leverx.nvasilyeva.pet.entity.Cat;
import com.leverx.nvasilyeva.pet.entity.Dog;
import com.leverx.nvasilyeva.pet.entity.PetType;
import com.leverx.nvasilyeva.pet.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository  extends JpaRepository<Dog, Long> {

    List<Dog> findAllBySize(Size size);

}
