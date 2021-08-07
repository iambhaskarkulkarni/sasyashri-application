package com.kulkarni.email.controller;

import com.kulkarni.email.model.MailModel;
import com.kulkarni.email.model.RequestDTO;
import com.kulkarni.email.service.SendingEmailService;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/email-service")
@CrossOrigin
public class MailSenderController {

    private static Logger log = LoggerFactory.getLogger(MailSenderController.class);

    @Autowired
    private SendingEmailService sendingEmailService;

    @PostMapping("/send-mail")
    public String sendmail(@RequestBody RequestDTO requestDTO) throws MessagingException, IOException, TemplateException {
        log.info("request recevied :- "+requestDTO);
        MailModel mailModel = new MailModel();
        mailModel.setFrom("KulkarniSoftech@gmail.com");
        mailModel.setTo(requestDTO.getMail());
        mailModel.setSubject("SASYASHRI LIMITED");
        mailModel.setName(requestDTO.getFirst_name()+" "+requestDTO.getLast_name());
        mailModel.setContent("We look forward to chatting soon!");
        sendingEmailService.sendEmail(mailModel);
        return "Request Received Successfully";
    }
}
