package be.ehb.enterpriseapp.service;

import be.ehb.enterpriseapp.web.ContactForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Verstuurt contactberichten naar de administrators.
 * Tijdens ontwikkeling kan dit via mailtrap.io (zie application.properties).
 */
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final String adminAddress;
    private final String fromAddress;

    public MailService(JavaMailSender mailSender,
                       @Value("${app.mail.to}") String adminAddress,
                       @Value("${app.mail.from}") String fromAddress) {
        this.mailSender = mailSender;
        this.adminAddress = adminAddress;
        this.fromAddress = fromAddress;
    }

    public void sendContactMessage(ContactForm form) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminAddress);
        message.setFrom(fromAddress);
        message.setReplyTo(form.getEmail());
        message.setSubject("Nieuw contactbericht van " + form.getNaam());
        message.setText(
                "Naam: " + form.getNaam() + "\n"
                        + "E-mail: " + form.getEmail() + "\n\n"
                        + form.getBericht());
        mailSender.send(message);
    }
}
