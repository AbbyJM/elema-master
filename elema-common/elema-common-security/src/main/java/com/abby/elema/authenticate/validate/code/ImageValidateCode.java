package com.abby.elema.authenticate.validate.code;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import java.awt.image.BufferedImage;

/**
 * the default implementation for image validate code
 * @author: Abby
 */
public class ImageValidateCode implements ValidateCode {

    private BufferedImage validateImage;
    private String validateType;
    private long expireTime;
    private String validateText;

    public ImageValidateCode(String type,Object image,long expire,String text){
        validateImage=(BufferedImage) image;
        validateType=type;
        expireTime=expire;
        validateText=text;
    }

    @Override
    public String getValidateType() {
        return validateType;
    }

    @Override
    public BufferedImage getValidateCode() {
        return validateImage;
    }

    @Override
    public long getExpiredTime() {
        return expireTime;
    }

    @Override
    public String getValidateText(){
        return validateText;
    }
}
