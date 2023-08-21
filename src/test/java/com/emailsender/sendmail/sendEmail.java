package com.emailsender.sendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class sendEmail {

    private final JavaMailSender mailSender;

    @Autowired
    public sendEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String toEmail, String subject, String body, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("inventorymanagementsystem2023@gmail.com");
            helper.setTo(toEmail);
            helper.setText(body);
            helper.setSubject(subject);

            // Attach PDF file
            Path path = Paths.get(filePath);
            byte[] pdfBytes = Files.readAllBytes(path);
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);
            helper.addAttachment("attachment.pdf", resource);

            mailSender.send(message);
            System.out.println("Mail sent successfully");
        } catch (MessagingException | IOException e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}
