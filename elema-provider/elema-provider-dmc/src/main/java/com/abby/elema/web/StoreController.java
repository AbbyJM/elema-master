package com.abby.elema.web;

import com.abby.elema.model.dto.RegisterStoreDto;
import com.abby.elema.model.enums.StoreStatusEnum;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Abby
 */
@RestController
public class StoreController {

    @RequestMapping(value = "/api/store/register",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String registerStore(HttpServletRequest request, HttpServletResponse response){
        String sellerName=request.getParameter("seller");
        String sellerId=request.getParameter("id");
        String name=request.getParameter("name");
        String pic=request.getParameter("pic");
        RegisterStoreDto registerStoreDto=new RegisterStoreDto();
        registerStoreDto.setSellerName(sellerName);
        registerStoreDto.setName(name);
        registerStoreDto.setPic(pic);
        registerStoreDto.setStatus(StoreStatusEnum.NORMAL.getStatus());
        registerStoreDto.setSellerId(Integer.parseInt(sellerId));

        return null;
    }
}
