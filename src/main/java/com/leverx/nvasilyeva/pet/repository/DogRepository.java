package com.leverx.nvasilyeva.pet.repository;

import com.leverx.nvasilyeva.pet.entity.Cat;
import com.leverx.nvasilyeva.pet.entity.Dog;
import com.leverx.nvasilyeva.pet.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository  extends JpaRepository<Dog, Long> {

    Cat findAllBySize(Size size);
    Cat findAllByOwnerIdAndSize(String ownerId, Size size);
}
