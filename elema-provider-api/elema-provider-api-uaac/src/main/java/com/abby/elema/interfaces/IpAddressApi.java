package com.abby.elema.interfaces;

import com.abby.elema.feign.FeignClientOauth2Config;
import com.abby.elema.hystrix.IpAddressFeignFallback;
import com.abby.elema.model.GaodeIpVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Abby
 */
@FeignClient(name = "elema-provider-uaac",fallback = IpAddressFeignFallback.class,configuration = FeignClientOauth2Config.class)
public interface IpAddressApi {

    @RequestMapping(method = RequestMethod.POST,value = "/api/getIpLocation")
    GaodeIpVo getIpLocation(@RequestParam("ip") String ip);

}