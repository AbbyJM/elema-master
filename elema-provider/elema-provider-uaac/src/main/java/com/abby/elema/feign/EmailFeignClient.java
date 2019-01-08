package com.abby.elema.feign;

import com.abby.elema.interfaces.EmailApi;
import com.abby.elema.model.dto.MailMessageDto;
import com.abby.elema.service.EmailService;

import com.abby.elema.wrapper.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author: Abby
 */
@RestController
public class EmailFeignClient implements EmailApi {

    @Resource
    private EmailService emailService;

    @Override
    public String sendEmail(@RequestBody MailMessageDto mailMessageDto) throws JsonProcessingException {
        emailService.sendEmail(mailMessageDto);
        return ResponseWrapper.ok("sent email successfully");
    }

    @Override
    public String sendUserActivationEmail(@RequestBody MailMessageDto mailMessageDto) throws IOException, TemplateException, MessagingException {
        emailService.sendUserActivationEmail(mailMessageDto);
        return ResponseWrapper.ok("sent email successfully");
    }
}
