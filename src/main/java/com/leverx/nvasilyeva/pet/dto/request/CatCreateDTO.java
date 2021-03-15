package com.leverx.nvasilyeva.pet.dto.request;

import lombok.Data;

@Data
public class CatCreateDTO extends PetCreateDTO {

    private String color;
}
