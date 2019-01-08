package com.abby.elema.service.impl;

import com.abby.elema.interfaces.UserApi;
import com.abby.elema.mapper.StoreMapper;
import com.abby.elema.model.domain.Store;
import com.abby.elema.model.dto.RegisterStoreDto;
import com.abby.elema.model.enums.StoreStatusEnum;
import com.abby.elema.service.StoreService;
import com.abby.elema.util.LogUtil;
import com.abby.elema.wrapper.ResponseObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: Abby
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private UserApi userApi;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public List<Store> getAllStores() {
        return storeMapper.getAllStores();
    }

    @Override
    public boolean registerStore(RegisterStoreDto registerStoreDto) throws IOException {
        if(!checkStoreRegisterDto(registerStoreDto)){
            return false;
        }

        String isSeller=userApi.isUserSeller(registerStoreDto.getName());
        ResponseObject result=objectMapper.readValue(isSeller,ResponseObject.class);
        if(!(boolean)result.getResult()){
            LogUtil.info("the user is not seller");
            return false;
        }

        Store store=new Store();
        store.setName(registerStoreDto.getName());
        store.setLocalton(registerStoreDto.getLocation());
        store.setSellerId(registerStoreDto.getSeller_id());
        store.setSellerName(registerStoreDto.getSellerName());
        store.setPic(registerStoreDto.getPic());
        store.setStatus(registerStoreDto.getStatus());

        return storeMapper.insert(store)>0;
    }

    @Override
    public boolean isStoreStatusNormal(int id) {
        String status=storeMapper.getStoreStatus(id);
        return status.equals(StoreStatusEnum.NORMAL.getStatus());
    }

    @Override
    public List<Store> getStoresOfPage(int pageNum, int pageSize) {

        return null;
    }

    private boolean checkStoreRegisterDto(RegisterStoreDto registerStoreDto){
        return registerStoreDto.getName() != null && registerStoreDto.getLocation() != null
                && registerStoreDto.getSellerName() != null;
    }
}
