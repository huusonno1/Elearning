package com.codewithson.Elearning.response.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleResponse {
    @JsonProperty("name_module")
    private String nameModule;

    @JsonProperty("description")
    private String description;

    @JsonProperty("number")
    private Long number;

    @JsonProperty("course_id")
    private Long courseId;
}
