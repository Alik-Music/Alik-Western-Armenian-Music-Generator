package com.balians.musicgen.prompttemplate.controller;

import com.balians.musicgen.common.response.StandardSuccessResponse;
import com.balians.musicgen.prompttemplate.dto.CreatePromptTemplateRequest;
import com.balians.musicgen.prompttemplate.dto.PromptTemplateResponse;
import com.balians.musicgen.prompttemplate.dto.UpdatePromptTemplateRequest;
import com.balians.musicgen.prompttemplate.service.PromptTemplateService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/prompt-templates")
public class PromptTemplateController {

    private final PromptTemplateService promptTemplateService;

    @PostMapping
    public StandardSuccessResponse<PromptTemplateResponse> createTemplate(
            @Valid @RequestBody CreatePromptTemplateRequest request
    ) {
        return StandardSuccessResponse.ok(promptTemplateService.createTemplate(request));
    }

    @PutMapping("/{id}")
    public StandardSuccessResponse<PromptTemplateResponse> updateTemplate(
            @PathVariable String id,
            @Valid @RequestBody UpdatePromptTemplateRequest request
    ) {
        return StandardSuccessResponse.ok(promptTemplateService.updateTemplate(id, request));
    }

    @GetMapping("/{id}")
    public StandardSuccessResponse<PromptTemplateResponse> getTemplateById(@PathVariable String id) {
        return StandardSuccessResponse.ok(promptTemplateService.getTemplateById(id));
    }

    @GetMapping
    public StandardSuccessResponse<List<PromptTemplateResponse>> listTemplates(
            @RequestParam(required = false) String projectId,
            @RequestParam(required = false) Boolean activeOnly
    ) {
        return StandardSuccessResponse.ok(promptTemplateService.listTemplates(projectId, activeOnly));
    }
}
