package com.leverx.nvasilyeva.pet.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class OwnerCreateDTO {

    private String username;

    @NotNull
    @Pattern(message ="Please use pattern", regexp = "((?=.*d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")
    private String password;

    @NotNull
    @Pattern(message ="Please use pattern", regexp = "((?=.*d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")
    private String confirmPassword;

    @Email(message = "Email address has invalid format: ${validatedValue}",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @DateTimeFormat(pattern = "DD-MM-YYYY")
    private LocalDate birthdate;

    private String role;

}
