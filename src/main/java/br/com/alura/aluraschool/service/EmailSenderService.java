package br.com.alura.aluraschool.service;

import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    public static void send(String recipientEmail, String subject, String body) {
        System.out.println(
                "Simulating sending email to [%s]:\n".formatted(recipientEmail)
        );
        System.out.println("""
                           Subject: %s
                            Body: %s
                           """.formatted(subject, body));
    }
}