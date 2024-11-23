package org.example.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ProductDTO {

    private Long id;
    @NotBlank(message = "Будьте любезны, впишите минимум 3 буквы")
    @Size(min = 3, max = 200, message = "Минимум 3, максимум 200")
    private String name;
    private Short amount;
    @NotNull(message = "id не может быть нулевым")
    private Long categoryId;
    @NotNull(message = "id не может быть нулевым")
    private Long availabilityId;

}
