package com.abby.elema.hystrix;

import com.abby.elema.interfaces.StoreApi;
import com.abby.elema.model.dto.RegisterStoreDto;

/**
 * @author: Abby
 */
public class StoreFeignFallback implements StoreApi {
    @Override
    public String registerStore(RegisterStoreDto registerStoreDto) {
        return null;
    }
}
