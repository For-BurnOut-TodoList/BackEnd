package com.example.todolist.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.todolist.backend.dto.MailRequestDto;

import lombok.RequiredArgsConstructor;
/*
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.Body;
import software.amazon.awssdk.services.sesv2.model.Content;
import software.amazon.awssdk.services.sesv2.model.Destination;
import software.amazon.awssdk.services.sesv2.model.EmailContent;
import software.amazon.awssdk.services.sesv2.model.Message;
import software.amazon.awssdk.services.sesv2.model.SendEmailRequest;
 */

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${email.sender}")
    private String senderEmail;

    @Value("${email.counselor}")
    private String counselorEmail;

    @Value("${email.region}")
    private String regionString;

    public void sendConsultMail(MailRequestDto dto) {
        /*
        Region region = Region.of(regionString);
        SesV2Client client = SesV2Client.builder()
                .region(region)
                .build();

         */

        String subject = "새 상담 요청이 도착했습니다.";
        String body = "From: " + dto.getFromEmail() + "\n\n" +
                "Content:\n" + dto.getContent();

        /*
        SendEmailRequest request = SendEmailRequest.builder()
                .destination(Destination.builder()
                        .toAddresses(counselorEmail)
                        .build())
                .fromEmailAddress(senderEmail)
                .content(EmailContent.builder()
                        .simple(Message.builder()
                                .subject(Content.builder().data(subject).build())
                                .body(Body.builder()
                                        .text(Content.builder().data(body).build())
                                        .build())
                                .build())
                        .build())
                .build();

        client.sendEmail(request);

         */

        System.out.println("=== 상담 요청 메일 (Mock Print) ===");
        System.out.println("Subject: " + subject);
        System.out.println("To: " + counselorEmail);
        System.out.println("Body:\n" + body);
    }
}
