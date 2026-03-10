package com.balians.musicgen.prompttemplate.repository;

import com.balians.musicgen.prompttemplate.model.PromptTemplate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PromptTemplateRepository extends MongoRepository<PromptTemplate, String> {

    List<PromptTemplate> findByProjectIdOrderByCreatedAtDesc(String projectId);

    List<PromptTemplate> findByActiveTrueOrderByCreatedAtDesc();
}
