package org.sci.finalproject.SportParkBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping({"/contact"})
    public String showContactForm()
    {
        return "contact_form";
    }

    @PostMapping("/submitContact")
    public String submitContact(HttpServletRequest request)
    {
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setFrom( email);
        emailMessage.setTo("sportparkbooking@gmail.com");

        String mailSubject = firstName + lastName + " has sent a message ";
        String mailContent = "Sender Name " + firstName + "\n";
        mailContent += "Sender E-mail: " + email;
        mailContent += "Subject: " + subject +  "\n";
        mailContent += "Content: " + content + "\n";


        emailMessage.setFrom(email);
        emailMessage.setSubject(mailSubject);
        emailMessage.setText(mailContent);

        mailSender.send(emailMessage);
        return "message";
    }

}
