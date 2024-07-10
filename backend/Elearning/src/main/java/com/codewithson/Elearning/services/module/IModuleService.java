package com.codewithson.Elearning.services.module;

import com.codewithson.Elearning.dtos.ModuleDTO;
import com.codewithson.Elearning.response.module.ModuleListResponse;
import com.codewithson.Elearning.response.module.ModuleResponse;

import java.util.List;

public interface IModuleService {
    ModuleResponse createModule(Long courseId, ModuleDTO moduleDTO) throws Exception;

    ModuleListResponse getAllModules(Long courseId) throws Exception;

    ModuleResponse getModuleById(Long courseId, Long moduleId) throws Exception;

    ModuleResponse updateModule(Long courseId, Long moduleId, ModuleDTO moduleDTO) throws Exception;

    void deleteModule(Long courseId, Long moduleId);
}
