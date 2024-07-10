package com.codewithson.Elearning.dtos;

import com.codewithson.Elearning.models.Course;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {

    @JsonProperty("name_module")
    private String nameModule;

    @JsonProperty("description")
    private String description;

    @JsonProperty("number")
    private Long number;

    @JsonProperty("course_id")
    private Long courseId;
}
