package com.codewithson.Elearning.services.lesson;

import com.codewithson.Elearning.dtos.LessonDTO;
import com.codewithson.Elearning.response.lesson.LessonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ILessonService {
    LessonResponse createLesson(Long moduleId, LessonDTO lessonDTO) throws Exception;

    LessonResponse createVideo(Long lessonId, MultipartFile file) throws Exception;;
}
