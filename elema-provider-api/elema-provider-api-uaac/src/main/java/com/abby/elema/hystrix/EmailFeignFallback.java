package com.abby.elema.hystrix;

import com.abby.elema.interfaces.EmailApi;
import com.abby.elema.model.dto.MailMessageDto;

/**
 * @author: Abby
 */
public class EmailFeignFallback implements EmailApi {


    @Override
    public String sendEmail(MailMessageDto mailMessageDto) {
        return null;
    }

    @Override
    public String sendUserActivationEmail(MailMessageDto mailMessageDto) {
        return null;
    }
}
