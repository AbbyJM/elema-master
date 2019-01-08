package com.abby.elema.service;

import com.abby.elema.model.dto.MailMessageDto;
import com.abby.elema.model.dto.ResetPasswordDto;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author: Abby
 */
public interface EmailService {

    /**
     * send an email
     * @param mailMessageDto the message dto
     */
    void sendEmail(MailMessageDto mailMessageDto);

    /**
     * send a user activation email
     * @param mailMessageDto the message dto
     */
    void sendUserActivationEmail(MailMessageDto mailMessageDto) throws IOException, TemplateException, MessagingException;


    void sendResetPasswordEmail(ResetPasswordDto resetPasswordDto) throws IOException;
}
