package com.codewithson.Elearning.services.course;

import com.codewithson.Elearning.dtos.CourseDTO;
import com.codewithson.Elearning.exceptions.DataNotFoundException;
import com.codewithson.Elearning.models.Category;
import com.codewithson.Elearning.models.Course;
import com.codewithson.Elearning.models.User;
import com.codewithson.Elearning.repositories.CategoryRepo;
import com.codewithson.Elearning.repositories.CourseRepo;
import com.codewithson.Elearning.repositories.UserRepo;
import com.codewithson.Elearning.response.course.CourseListResponse;
import com.codewithson.Elearning.response.course.CourseResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService{
    private final CategoryRepo categoryRepo;
    private final CourseRepo courseRepo;
    private final UserRepo userRepo;

    @Override
    @Transactional
    public CourseResponse  createCourse(CourseDTO courseDTO) throws Exception {
        Category category = categoryRepo.findById(courseDTO.getCategoryId())
                .orElseThrow(()-> new DataNotFoundException("cannot find category"));

        User admin = userRepo.findById(courseDTO.getAdminId())
                .orElseThrow(() -> new DataNotFoundException("Admin not found"));

        Course newCourse = Course.builder()
                .nameCourse(courseDTO.getNameCourse())
                .description(courseDTO.getDescription())
                .price(courseDTO.getPrice())
                .category(category)
                .thumbnail(courseDTO.getThumbnail())
                .admin(admin)
                .build();
        Course savedCourse = courseRepo.save(newCourse);

        CourseResponse courseResponse = CourseResponse.builder()
                .nameCourse(savedCourse.getNameCourse())
                .description(savedCourse.getDescription())
                .price(savedCourse.getPrice())
                .categoryId(savedCourse.getCategory().getId())
                .thumbnail(savedCourse.getThumbnail())
                .adminId(savedCourse.getAdmin().getId())
                .build();

        return courseResponse;
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        Optional<Course> optionalCourse = courseRepo.findById(id);
        optionalCourse.ifPresent(courseRepo::delete);
    }

    @Override
    public Course getCourseById(Long id) throws Exception {
        Optional<Course> optionalCourse = courseRepo.getDetailCourse(id);
        if(optionalCourse.isPresent()){
            return optionalCourse.get();
        } throw new DataNotFoundException("Cannot find Course with id = " + id);
    }

    @Override
    public CourseResponse updateCourse(CourseDTO courseDTO, Long id) throws Exception{
        Optional<Course> optionalCourse = courseRepo.findById(id);
        if (!optionalCourse.isPresent()) {
            throw new Exception("Course not found with id: " + id);
        }
        Course newCourse = optionalCourse.get();

        // check course is created by admin id
        if (courseDTO.getAdminId() != null && !courseDTO.getAdminId().equals(newCourse.getAdmin().getId())) {
            throw new Exception("Course is not created by admin id :" + courseDTO.getAdminId());
        }
        User admin = userRepo.findById(courseDTO.getAdminId())
                .orElseThrow(() -> new DataNotFoundException("Admin not found"));
        newCourse.setAdmin(admin);

        if (courseDTO.getNameCourse() != null && !courseDTO.getNameCourse().equals(newCourse.getNameCourse())) {
            newCourse.setNameCourse(courseDTO.getNameCourse());
        }

        if (courseDTO.getDescription() != null && !courseDTO.getDescription().equals(newCourse.getDescription())) {
            newCourse.setDescription(courseDTO.getDescription());
        }

        if (courseDTO.getPrice() > 0 && courseDTO.getPrice() != newCourse.getPrice()) {
            newCourse.setPrice(courseDTO.getPrice());
        }
        if (courseDTO.getThumbnail() != null && !courseDTO.getThumbnail().equals(newCourse.getThumbnail())) {
            newCourse.setThumbnail(courseDTO.getThumbnail());
        }
        if (courseDTO.getCategoryId() != null && courseDTO.getCategoryId() != newCourse.getCategory().getId()) {
            Category category = categoryRepo.findById(courseDTO.getCategoryId())
                    .orElseThrow(()-> new DataNotFoundException("cannot find category"));
            newCourse.setCategory(category);
        }

        Course savedCourse = courseRepo.save(newCourse);

        CourseResponse courseResponse = CourseResponse.builder()
                .nameCourse(savedCourse.getNameCourse())
                .description(savedCourse.getDescription())
                .price(savedCourse.getPrice())
                .categoryId(savedCourse.getCategory().getId())
                .thumbnail(savedCourse.getThumbnail())
                .adminId(savedCourse.getAdmin().getId())
                .build();

        return courseResponse;
    }

    @Override
    public CourseListResponse getCourses(Long categoryId, PageRequest pageRequest) {
        return null;
    }
}
