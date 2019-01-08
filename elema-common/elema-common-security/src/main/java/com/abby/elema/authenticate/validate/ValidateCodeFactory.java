package com.abby.elema.authenticate.validate;

import com.abby.elema.authenticate.validate.code.ImageValidateCode;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.awt.image.BufferedImage;
import java.util.Properties;


/**
 * @author: Abby
 */
public class ValidateCodeFactory {

    private static volatile DefaultKaptcha captcha;

    private static long expireTime=600;

    /**
     * synchronized used here may reduce performance
     * but the image code method will not be invoked frequently
     * @return the ImageValidateCode
     */
    public static ImageValidateCode getImageValidateCode(){
         if(captcha==null){
             synchronized (DefaultKaptcha.class){
                 if(captcha==null){
                     captcha=new DefaultKaptcha();
                     configCaptcha(captcha);
                 }
             }
         }
         String text=captcha.createText();
         BufferedImage validateImage=captcha.createImage(text);

        return new ImageValidateCode("IMAGE",validateImage,expireTime,text);
    }

    /**
     * config the captcha
     * @param captcha the captcha to be configured
     */
    private static void configCaptcha(DefaultKaptcha captcha){

        Properties properties=new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "78,129,60");
        properties.setProperty("kaptcha.textproducer.font.color", "red");
        properties.setProperty("kaptcha.image.width", "150");
        properties.setProperty("kaptcha.image.height", "60");
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config=new Config(properties);
        captcha.setConfig(config);
    }

    public static void setExpireTime(long time){
        expireTime=time;
    }
}
