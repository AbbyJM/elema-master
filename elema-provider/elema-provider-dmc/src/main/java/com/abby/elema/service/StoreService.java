package com.abby.elema.service;

import com.abby.elema.model.domain.Store;
import com.abby.elema.model.dto.RegisterStoreDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

/**
 * @author: Abby
 */
public interface StoreService {

    /**
     * get all the stores
     * @return a list of the stores
     */
    List<Store> getAllStores();

    boolean  registerStore(RegisterStoreDto registerStoreDto) throws IOException;

    boolean  isStoreStatusNormal(int id);

    List<Store> getStoresOfPage(int pageNum,int pageSize);
}
