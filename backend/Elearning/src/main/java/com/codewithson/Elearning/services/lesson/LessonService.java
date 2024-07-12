package com.codewithson.Elearning.services.lesson;

import com.codewithson.Elearning.dtos.LessonDTO;
import com.codewithson.Elearning.exceptions.DataNotFoundException;
import com.codewithson.Elearning.models.Course;
import com.codewithson.Elearning.models.Lesson;
import com.codewithson.Elearning.models.Module;
import com.codewithson.Elearning.repositories.LessonRepo;
import com.codewithson.Elearning.repositories.ModuleRepo;
import com.codewithson.Elearning.response.lesson.LessonResponse;
import com.codewithson.Elearning.services.awss3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService{

    private final S3Service s3Service;
    private final ModuleRepo moduleRepo;
    private final LessonRepo lessonRepo;

    @Value("${aws.cloudfront.domainName}")
    private String domainName;

    @Override
    public LessonResponse createLesson(
            Long moduleId,
            LessonDTO lessonDTO
    ) throws Exception {

        Module module = moduleRepo.findById(moduleId)
                .orElseThrow(() -> new DataNotFoundException("Admin not found"));

        Lesson lesson = Lesson.builder()
                .nameLesson(lessonDTO.getNameLesson())
                .module(module)
                .document(lessonDTO.getDocument())
                .description(lessonDTO.getDescription())
                .thumbnail(lessonDTO.getThumbnail())
                .build();
        Lesson saved = lessonRepo.save(lesson);

        return LessonResponse.builder()
                .nameLesson(saved.getNameLesson())
                .moduleId(saved.getModule().getId())
                .document(saved.getDocument())
                .description(saved.getDescription())
                .thumbnail(saved.getThumbnail())
                .videoUrl(saved.getVideoUrl())
                .build();
    }

    @Override
    public LessonResponse createVideo(Long lessonId, MultipartFile file) throws Exception {
        Lesson lesson = lessonRepo.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException("Admin not found"));

        String videoUrl = "https://" + domainName +"/"+ s3Service.uploadFile(file);

        lesson.setVideoUrl(videoUrl);

        Lesson saved = lessonRepo.save(lesson);
        return LessonResponse.builder()
                .nameLesson(saved.getNameLesson())
                .moduleId(saved.getModule().getId())
                .document(saved.getDocument())
                .description(saved.getDescription())
                .thumbnail(saved.getThumbnail())
                .videoUrl(saved.getVideoUrl())
                .build();
    }
}
