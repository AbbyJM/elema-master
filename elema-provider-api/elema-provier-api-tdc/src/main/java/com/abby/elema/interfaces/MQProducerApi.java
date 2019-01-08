package com.abby.elema.interfaces;

import com.abby.elema.exception.RocketMqException;
import com.abby.elema.feign.FeignClientOauth2Config;
import com.abby.elema.hystrix.MQProducerFeignFallback;
import com.abby.elema.model.dto.RocketMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Abby
 */
@FeignClient(name = "elema-provider-tdc",fallback = MQProducerFeignFallback.class,configuration = FeignClientOauth2Config.class)
public interface MQProducerApi {

    @RequestMapping(value = "/admin/mqproducer/send",method = RequestMethod.POST,produces = "application/json")
    String sendTransactionMessage(@RequestBody RocketMessageDto rocketMessageDto) throws JsonProcessingException, RocketMqException;
}
