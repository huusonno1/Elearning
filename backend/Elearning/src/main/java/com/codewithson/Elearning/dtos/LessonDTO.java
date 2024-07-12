package com.codewithson.Elearning.dtos;

import com.codewithson.Elearning.models.Module;
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
public class LessonDTO {
    @JsonProperty("name_lesson")
    private String nameLesson;

    @JsonProperty("document")
    private String document;

    @JsonProperty("description")
    private String description;

    @JsonProperty("thumbnail")
    private String thumbnail;

}
