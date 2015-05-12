package com.umeca.service.detentionRecord;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.detentionRecord.DetainedDto;

/**
 * Created by Rata on 10/05/2015.
 */
public interface DetentionRecordService {
    ResponseMessage saveDetained(DetainedDto dto);

    ResponseMessage doProsecute(Long id);
}
