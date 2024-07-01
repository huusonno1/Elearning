package com.codewithson.Elearning.response.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    @JsonProperty("name_course")
    private String nameCourse;

    @JsonProperty("description")
    private String description = "";

    @JsonProperty("price")
    private Float price;

    @JsonProperty("thumbnail")
    private String thumbnail = "";

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("admin_id")
    private Long adminId;
}
