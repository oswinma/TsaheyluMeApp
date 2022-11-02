package me.tsaheylu.serviceImpl;


import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.TokenType;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import me.tsaheylu.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;
    private final FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${spring.mail.default-from}")
    private String from;

    @Value("${tsahayluMe.app.frontendUrl}")
    private String frontendUrl;
    private final RefreshTokenServiceImpl refreshTokenService;

    @Override
    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abc@qq.com");
        message.setTo("efd@qq.com");
        message.setSubject("Test send simple mail message");
        message.setText("Hello world!");

        mailSender.send(message);
    }


    private void sendWithTemplate(String to, String subject, String templateName, HashMap<String, Object> data) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);

            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (Exception ex) {
            logger.error("Failed to send email to. error={}", ex.getMessage());
        }
    }

    @Override
    @Async
    public void sendEmailVerificationEmail(User user) {
        String subject = "Email verification";
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user, TokenType.VERIFY);
        HashMap<String, Object> data = new HashMap<>();
        String url = frontendUrl + "/verify?token=" + refreshToken.getToken();
        data.put("url", url);
        data.put("user", user);
        sendWithTemplate(user.getEmail(), subject, "emailVerificationRequired.flt", data);
        logger.info("Email verification email sent to {}", user.getEmail());
    }


    @Override
    @Async
    public void sendResetPasswordEmail(User user) {
        String subject = "Reset Password";
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user, TokenType.VERIFY);
        HashMap<String, Object> data = new HashMap<>();
        String url = frontendUrl + "/resetPassword?token=" + refreshToken.getToken();
        data.put("url", url);
        data.put("user", user);
        sendWithTemplate(user.getEmail(), subject, "emailVerificationRequired.flt", data);
        logger.info("Password reset email sent to {}", user.getEmail());
    }
}
