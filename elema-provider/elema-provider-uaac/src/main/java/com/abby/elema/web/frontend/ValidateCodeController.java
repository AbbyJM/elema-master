package com.abby.elema.web.frontend;

import com.abby.elema.annotation.SaveLog;
import com.abby.elema.authenticate.validate.ValidateCodeFactory;
import com.abby.elema.authenticate.validate.ValidatorRepository;
import com.abby.elema.authenticate.validate.code.ImageValidateCode;

import com.abby.elema.model.enums.OperationEnum;
import com.abby.elema.util.LogUtil;
import com.abby.elema.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidatorFactory;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Abby
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidatorRepository validatorRepository;

    @SaveLog(type = OperationEnum.SEND_EMAIL)
    @PostMapping(value = "/api/getvalidate/image",produces = "application/json")
    public String getImageValidateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //if we want to return the image stream
        /*ServletOutputStream outputStream=response.getOutputStream();
        response.setContentType("image/jpeg");
        ImageIO.write(imageCode,"jpg",outputStream);
        try{
            outputStream.flush();
        }finally {
            outputStream.close();
        }*/

        //here we return the base64 encoded picture
        ImageValidateCode validateCode=ValidateCodeFactory.getImageValidateCode();
        BufferedImage imageCode=validateCode.getValidateCode();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ImageIO.write(imageCode,"jpg",byteArrayOutputStream);
        Base64Encoder encoder=new Base64Encoder();
        String base64Encoded=encoder.encode(byteArrayOutputStream.toByteArray());
        String base64Image="data:image/png;base64,"+base64Encoded;
        Map<String,Object> result=new HashMap<>(3);
        result.put("status",200);
        result.put("message","generated validator image successfully");
        result.put("data",base64Image);

        LogUtil.info("validator code is "+validateCode.getValidateText());
        //save the image validator
        validatorRepository.saveValidator(validateCode);
        return ResponseWrapper.response(result);
    }
}
