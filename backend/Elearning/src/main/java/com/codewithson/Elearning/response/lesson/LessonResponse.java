package com.codewithson.Elearning.response.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponse {
    @JsonProperty("name_lesson")
    private String nameLesson;

    @JsonProperty("video_url")
    private String videoUrl;

    @JsonProperty("document")
    private String document;

    @JsonProperty("description")
    private String description;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("module_id")
    private Long moduleId;
}
