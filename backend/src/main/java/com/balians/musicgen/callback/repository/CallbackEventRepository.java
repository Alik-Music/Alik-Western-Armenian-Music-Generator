package com.balians.musicgen.callback.repository;

import com.balians.musicgen.callback.model.CallbackEvent;
import com.balians.musicgen.common.enums.CallbackProcessingStatus;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CallbackEventRepository extends MongoRepository<CallbackEvent, String> {

    List<CallbackEvent> findByProviderTaskIdOrderByReceivedAtDesc(String providerTaskId);

    List<CallbackEvent> findByProcessingStatusOrderByReceivedAtAsc(CallbackProcessingStatus processingStatus);
}
