package com.balians.musicgen.prompttemplate.service;

import com.balians.musicgen.common.exception.BadRequestException;
import com.balians.musicgen.common.exception.NotFoundException;
import com.balians.musicgen.prompttemplate.dto.CreatePromptTemplateRequest;
import com.balians.musicgen.prompttemplate.dto.PromptTemplateResponse;
import com.balians.musicgen.prompttemplate.dto.UpdatePromptTemplateRequest;
import com.balians.musicgen.prompttemplate.model.PromptTemplate;
import com.balians.musicgen.prompttemplate.repository.PromptTemplateRepository;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromptTemplateService {

    private final PromptTemplateRepository promptTemplateRepository;

    public PromptTemplateResponse createTemplate(CreatePromptTemplateRequest request) {
        validateTemplateRequest(request.projectId(), request.promptTemplate());

        PromptTemplate template = PromptTemplate.builder()
                .projectId(request.projectId().trim())
                .name(request.name().trim())
                .customMode(request.customMode())
                .instrumental(request.instrumental())
                .model(request.model())
                .promptTemplate(request.promptTemplate().trim())
                .styleTemplate(trimToNull(request.styleTemplate()))
                .titleTemplate(trimToNull(request.titleTemplate()))
                .active(request.active())
                .isDefault(request.isDefault())
                .build();

        PromptTemplate savedTemplate = promptTemplateRepository.save(template);
        log.info("Created prompt template id={} projectId={} isDefault={}",
                savedTemplate.getId(), savedTemplate.getProjectId(), savedTemplate.getIsDefault());

        return map(savedTemplate);
    }

    public PromptTemplateResponse updateTemplate(String id, UpdatePromptTemplateRequest request) {
        PromptTemplate template = promptTemplateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prompt template not found: " + id));

        validateTemplateRequest(template.getProjectId(), request.promptTemplate());

        template.setName(request.name().trim());
        template.setCustomMode(request.customMode());
        template.setInstrumental(request.instrumental());
        template.setModel(request.model());
        template.setPromptTemplate(request.promptTemplate().trim());
        template.setStyleTemplate(trimToNull(request.styleTemplate()));
        template.setTitleTemplate(trimToNull(request.titleTemplate()));
        template.setActive(request.active());
        template.setIsDefault(request.isDefault());

        PromptTemplate savedTemplate = promptTemplateRepository.save(template);
        log.info("Updated prompt template id={} projectId={} isDefault={}",
                savedTemplate.getId(), savedTemplate.getProjectId(), savedTemplate.getIsDefault());

        return map(savedTemplate);
    }

    public PromptTemplateResponse getTemplateById(String id) {
        PromptTemplate template = promptTemplateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prompt template not found: " + id));
        return map(template);
    }

    public List<PromptTemplateResponse> listTemplates(String projectId, Boolean activeOnly) {
        List<PromptTemplate> templates;

        if (hasText(projectId)) {
            templates = promptTemplateRepository.findByProjectIdOrderByCreatedAtDesc(projectId.trim());
        } else if (Boolean.TRUE.equals(activeOnly)) {
            templates = promptTemplateRepository.findByActiveTrueOrderByCreatedAtDesc();
        } else {
            templates = promptTemplateRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(PromptTemplate::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                    .toList();
        }

        return templates.stream().map(this::map).toList();
    }

    private void validateTemplateRequest(String projectId, String promptTemplate) {
        if (!hasText(projectId)) {
            throw new BadRequestException("projectId is required");
        }
        if (!hasText(promptTemplate)) {
            throw new BadRequestException("promptTemplate is required");
        }
    }

    private PromptTemplateResponse map(PromptTemplate template) {
        return new PromptTemplateResponse(
                template.getId(),
                template.getProjectId(),
                template.getName(),
                template.getCustomMode(),
                template.getInstrumental(),
                template.getModel(),
                template.getPromptTemplate(),
                template.getStyleTemplate(),
                template.getTitleTemplate(),
                template.getActive(),
                template.getIsDefault(),
                template.getCreatedAt(),
                template.getUpdatedAt()
        );
    }

    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private String trimToNull(String value) {
        if (!hasText(value)) {
            return null;
        }
        return value.trim();
    }
}
