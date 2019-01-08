package com.abby.elema.authenticate.validate.code;

/**
 * @author: Abby
 */
public interface ValidateCode {

    String getValidateType();

    Object getValidateCode();

    long getExpiredTime();

    String getValidateText();
}
