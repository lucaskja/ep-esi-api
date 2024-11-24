package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.EmailInformation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class EmailService {
    private static final String TEMPLATE_NAME = "reminder";
    private static final String MAIL_SUBJECT = "Pesquisa de Desempenho";

    private final Environment environment;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(EmailInformation user) throws MessagingException, UnsupportedEncodingException {
        String confirmationUrl = "generated_confirmation_url";
        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");

        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        email.setTo(user.email());
        email.setSubject(MAIL_SUBJECT);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = user.dateToSendDeadline().format(formatter);

        final Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", user.email());
        ctx.setVariable("name", user.name());
        ctx.setVariable("date", formattedDate);
        ctx.setVariable("url", confirmationUrl);

        final String htmlContent = this.templateEngine.process(TEMPLATE_NAME, ctx);

        email.setText(htmlContent, true);

        javaMailSender.send(mimeMessage);
    }
}
