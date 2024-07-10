package com.codewithson.Elearning.response.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleListResponse {
    private List<ModuleResponse> modules ;
}
