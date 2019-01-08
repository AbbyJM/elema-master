package com.abby.elema.interfaces;

import com.abby.elema.feign.FeignClientOauth2Config;
import com.abby.elema.hystrix.StoreFeignFallback;
import com.abby.elema.model.dto.RegisterStoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Abby
 */
@FeignClient(name="elema-provider-dmc",configuration = FeignClientOauth2Config.class,fallback = StoreFeignFallback.class)
public interface StoreApi {

    @RequestMapping(value = "/api/store/register",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String registerStore(@RequestBody RegisterStoreDto registerStoreDto);
}
