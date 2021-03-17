package com.leverx.nvasilyeva.pet.repository;

import com.leverx.nvasilyeva.pet.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findAllByColor(String color);
    List<Cat> findAllByOrderByOwnerId(long ownerId);

}
