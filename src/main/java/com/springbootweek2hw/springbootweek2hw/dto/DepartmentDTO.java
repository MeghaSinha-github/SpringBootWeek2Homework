package com.springbootweek2hw.springbootweek2hw.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springbootweek2hw.springbootweek2hw.annotations.PasswordValidation;
import com.springbootweek2hw.springbootweek2hw.annotations.PrimeNumberValidation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    @NotBlank(message="Name of the department cannot be blank")
    private String title;
    @AssertTrue(message = "Department should be active")
    @JsonProperty("isActive")
    private boolean isActive;
    @PrimeNumberValidation
    private Integer checkPrime;
    @PasswordValidation
    private String password;
    @PastOrPresent(message = "DateOfCreation field of Department cannot be in the future")
    private LocalDate createdAt;
}
