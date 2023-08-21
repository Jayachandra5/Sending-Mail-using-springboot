package com.emailsender.sendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SendmailApplicationTests {

    @Autowired
    private sendEmail senderService;

    public static void main(String[] args) {
        SpringApplication.run(SendmailApplicationTests.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        senderService.sendMail("jayachandra.kothamasu18@gmail.com", "Sending email",
                "Your Bill, Thanks For shopping","V:\\Project IMS\\bills\\bill proto.pdf");
    }
}
