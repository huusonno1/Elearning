package com.codewithson.Elearning.response.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonListResponse {
    private List<LessonResponse> lessons;
}
