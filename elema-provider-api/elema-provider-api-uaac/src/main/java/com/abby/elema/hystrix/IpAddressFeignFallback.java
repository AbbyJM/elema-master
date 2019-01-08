package com.abby.elema.hystrix;

import com.abby.elema.interfaces.IpAddressApi;
import com.abby.elema.model.GaodeIpVo;

/**
 * @author: Abby
 */
public class IpAddressFeignFallback implements IpAddressApi {
    @Override
    public GaodeIpVo getIpLocation(String ip) {
        return null;
    }
}
