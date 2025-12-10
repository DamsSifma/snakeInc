package org.snakeInc.api.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayerDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Min(value = 13, message = "Age must be at least 13")
    private int age;
}
