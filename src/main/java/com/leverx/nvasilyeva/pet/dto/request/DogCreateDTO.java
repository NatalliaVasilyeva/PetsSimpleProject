package com.leverx.nvasilyeva.pet.dto.request;

import lombok.Data;

@Data
public class DogCreateDTO extends PetCreateDTO {

    private String size;
}
