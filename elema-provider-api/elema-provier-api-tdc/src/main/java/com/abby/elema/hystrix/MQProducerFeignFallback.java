package com.abby.elema.hystrix;

import com.abby.elema.interfaces.MQProducerApi;
import com.abby.elema.model.dto.RocketMessageDto;

/**
 * @author: Abby
 */
public class MQProducerFeignFallback implements MQProducerApi {

    @Override
    public String sendTransactionMessage(RocketMessageDto rocketMessageDto) {
        return null;
    }
}
