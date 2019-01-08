package com.abby.elema.interfaces;


import com.abby.elema.hystrix.LogFeignFallback;


import com.abby.elema.model.dto.OperationDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author: Abby
 */
@FeignClient(name = "elema-provider-uaac",fallback = LogFeignFallback.class)
public interface LogFeignApi {

    @RequestMapping(method = RequestMethod.POST,value = "admin/log/save")
    void saveLog(@RequestBody OperationDto operationDto);

}
