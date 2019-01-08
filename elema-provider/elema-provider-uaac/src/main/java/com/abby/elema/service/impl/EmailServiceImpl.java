package com.abby.elema.service.impl;

import com.abby.elema.InstanceUrlsProperties;
import com.abby.elema.model.EmailTemplateEnums;
import com.abby.elema.model.constants.EmailConstants;
import com.abby.elema.model.constants.HttpConstants;
import com.abby.elema.model.dto.MailMessageDto;
import com.abby.elema.model.dto.ResetPasswordDto;
import com.abby.elema.service.EmailService;
import com.abby.elema.util.LogUtil;
import com.google.inject.internal.util.Preconditions;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: Abby
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Resource
    private RedisTemplate<String,Object> redisTemp;

    @Resource
    private InstanceUrlsProperties instanceUrlsProperties;

    @Override
    public void sendEmail(MailMessageDto mailMessageDto) {
        if(!checkEmail(mailMessageDto)){
            LogUtil.info("illegal arguments of the email dto");
            return;
        }
        SimpleMailMessage message=buildMessage(mailMessageDto);
        javaMailSender.send(message);
    }

    @Override
    public void sendUserActivationEmail(MailMessageDto mailMessageDto) throws IOException, TemplateException, MessagingException {
        if(!checkEmail(mailMessageDto)){
            return;
        }

        Template template=freeMarkerConfigurer.getConfiguration().getTemplate(EmailTemplateEnums.USER_ACTIVATION_TEMPLATE.getTemplateName(),"UTF-8");
        Map<String,String> params=new HashMap<>(2);
        params.put("userName",mailMessageDto.getUserName());
        String key=UUID.randomUUID().toString().replace("-","");
        String timeStamp=System.currentTimeMillis()+"";
        String userActiveUrl=instanceUrlsProperties.getUserActive()+"?username="+mailMessageDto.getUserName()
                +"&key="+key+"&timeStamp="+timeStamp;
        params.put("activeUserUrl",userActiveUrl);

        String messageText=FreeMarkerTemplateUtils.processTemplateIntoString(template,params);
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,false,"UTF-8");
        helper.setFrom(mailMessageDto.getFrom());
        helper.setTo(mailMessageDto.getTo());
        helper.setSubject(mailMessageDto.getSubject());
        helper.setText(messageText,true);
        javaMailSender.send(message);

        //valid for one hour by default
        redisTemp.opsForValue().set(mailMessageDto.getUserName(),userActiveUrl,1,TimeUnit.HOURS);
    }

    @Override
    public void sendResetPasswordEmail(ResetPasswordDto resetPasswordDto) throws IOException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(resetPasswordDto.getUserName()),"user name cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(resetPasswordDto.getEmail()),"email address cannot be null");
        MailMessageDto mailMessageDto=new MailMessageDto();
        mailMessageDto.setFrom(EmailConstants.DEFAULT_SENDER);
        mailMessageDto.setTo(resetPasswordDto.getEmail());
        mailMessageDto.setSubject("Reset your password for Elema");

        Template template=freeMarkerConfigurer.getConfiguration().getTemplate(EmailTemplateEnums.RESET_PASSWORD_TEMPLATE.getTemplateName(),"UTF-8");
        Map<String,String> params=new HashMap<>(3);
        params.put("userName",resetPasswordDto.getUserName());

    }

    private boolean checkEmail(MailMessageDto mailMessageDto){
        Preconditions.checkArgument(StringUtils.isNotEmpty(mailMessageDto.getFrom()),"the sender email cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(mailMessageDto.getTo()),"the email receiver cannot be null");
        return true;
    }

    private SimpleMailMessage buildMessage(MailMessageDto dto){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(dto.getFrom());
        message.setTo(dto.getTo());
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());
        return message;
    }
}