package com.codewithson.Elearning.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @NotBlank(message = "Name course is required")
    @Size(min = 3, max = 300, message = "Name must be between 3 and 300 characters")
    @JsonProperty("name_course")
    private String nameCourse;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than 0")
    @Max(value = 10000000, message = "Price must be less than 10000000")
    @JsonProperty("price")
    private Float price;

    private String thumbnail;

    @JsonProperty("category_id")
    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @JsonProperty("admin_id")
    @NotNull(message = "Admin ID is required")
    private Long adminId;

}