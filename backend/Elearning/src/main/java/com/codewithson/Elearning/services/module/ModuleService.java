package com.codewithson.Elearning.services.module;

import com.codewithson.Elearning.dtos.ModuleDTO;
import com.codewithson.Elearning.exceptions.DataNotFoundException;
import com.codewithson.Elearning.models.Course;
import com.codewithson.Elearning.models.Lesson;
import com.codewithson.Elearning.models.Module;
import com.codewithson.Elearning.repositories.CourseRepo;
import com.codewithson.Elearning.repositories.ModuleRepo;
import com.codewithson.Elearning.response.module.ModuleListResponse;
import com.codewithson.Elearning.response.module.ModuleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModuleService implements IModuleService{
    private final ModuleRepo moduleRepo;
    private final CourseRepo courseRepo;

    @Override
    public ModuleResponse createModule(Long courseId, ModuleDTO moduleDTO) throws Exception{
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new DataNotFoundException("Admin not found"));
        Module module = Module.builder()
                .nameModule(moduleDTO.getNameModule())
                .description(moduleDTO.getDescription())
                .number(moduleDTO.getNumber())
                .course(course)
                .build();
        Module moduleSaved = moduleRepo.save(module);

        ModuleResponse moduleResponse = ModuleResponse.builder()
                .nameModule(moduleSaved.getNameModule())
                .number(moduleSaved.getNumber())
                .description(moduleSaved.getDescription())
                .courseId(moduleSaved.getCourse().getId())
                .build();

        return moduleResponse;
    }

    @Override
    public ModuleListResponse getAllModules(Long courseId) throws Exception {
        if(courseId == null){
            throw new RuntimeException("course id is required");
        }
        List<Module> modules = moduleRepo.findByCourseId(courseId);
        List<ModuleResponse> moduleResponses = modules.stream()
                .map(module -> {
                    ModuleResponse moduleResponse = new ModuleResponse();
                    moduleResponse.setNameModule(module.getNameModule());
                    moduleResponse.setDescription(module.getDescription());
                    moduleResponse.setNumber(module.getNumber());
                    moduleResponse.setCourseId(module.getCourse().getId());

                    return moduleResponse;
                })
                .collect(Collectors.toList());
        return ModuleListResponse.builder()
                .modules(moduleResponses)
                .build();
    }

    @Override
    public ModuleResponse getModuleById(Long courseId, Long moduleId) throws Exception {
        if(courseId == null || moduleId == null){
            throw new RuntimeException("course id and module id is required");
        }
        Module module = moduleRepo.findByCourseIdAndId(courseId, moduleId)
                .orElseThrow(() -> new DataNotFoundException("module not found"));

        ModuleResponse moduleResponse = ModuleResponse.builder()
                .nameModule(module.getNameModule())
                .number(module.getNumber())
                .description(module.getDescription())
                .courseId(module.getCourse().getId())
                .build();
        return moduleResponse;
    }

    @Override
    public ModuleResponse updateModule(
            Long courseId,
            Long moduleId,
            ModuleDTO moduleDTO
    ) throws Exception {
        Optional<Module> optionalModule = moduleRepo.findByCourseIdAndId(courseId, moduleId);
        if (!optionalModule.isPresent()) {
            throw new Exception("Course not found module with id: " + moduleId);
        }
        Module newModule = optionalModule.get();

        if (moduleDTO.getNameModule() != null && !moduleDTO.getNameModule().equals(newModule.getNameModule())) {
            newModule.setNameModule(moduleDTO.getNameModule());
        }

        if (moduleDTO.getDescription() != null && !moduleDTO.getDescription().equals(newModule.getDescription())) {
            newModule.setDescription(moduleDTO.getDescription());
        }

        if (moduleDTO.getNumber() != null && !moduleDTO.getNumber().equals(newModule.getNumber())) {
            newModule.setNumber(moduleDTO.getNumber());
        }

        Module savedModule = moduleRepo.save(newModule);

        ModuleResponse moduleResponse = ModuleResponse.builder()
                .number(savedModule.getNumber())
                .nameModule(savedModule.getNameModule())
                .description(savedModule.getDescription())
                .courseId(savedModule.getCourse().getId())
                .build();

        return moduleResponse;
    }

    @Override
    public void deleteModule(Long courseId, Long moduleId) {
        Optional<Module> optionalModule = moduleRepo.findByCourseIdAndId(courseId, moduleId);
        optionalModule.ifPresent(moduleRepo::delete);
    }
}
