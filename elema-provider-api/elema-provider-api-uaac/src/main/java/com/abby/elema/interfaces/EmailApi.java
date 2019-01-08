package com.abby.elema.interfaces;

import com.abby.elema.feign.FeignClientOauth2Config;
import com.abby.elema.hystrix.EmailFeignFallback;
import com.abby.elema.model.dto.MailMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import freemarker.template.TemplateException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author: Abby
 */
@FeignClient(name = "elema-provider-uaac",fallback = EmailFeignFallback.class,configuration = FeignClientOauth2Config.class)
public interface EmailApi {

    @RequestMapping(value = "/admin/email/send",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String sendEmail(@RequestBody MailMessageDto mailMessageDto) throws JsonProcessingException;

    @RequestMapping(value = "/admin/email/active_user/send",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String sendUserActivationEmail(@RequestBody MailMessageDto mailMessageDto) throws IOException, TemplateException, MessagingException;
}
