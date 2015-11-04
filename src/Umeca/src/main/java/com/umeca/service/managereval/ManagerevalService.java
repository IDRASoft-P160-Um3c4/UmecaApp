package com.umeca.service.managereval;

import com.umeca.controller.managereval.SourcesDataView;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.reviewer.dto.RequestDto;


/**
 * Created by DeveloperII on 03/11/2015.
 */
public interface ManagerevalService {
    ResponseMessage save(SourcesDataView sourcesInfo, Long c);

    ResponseMessage doMakeRequest(RequestDto requestDto);


}
