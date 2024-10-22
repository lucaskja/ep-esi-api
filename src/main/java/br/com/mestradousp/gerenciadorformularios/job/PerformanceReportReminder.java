package br.com.mestradousp.gerenciadorformularios.job;

import br.com.mestradousp.gerenciadorformularios.dto.EmailInformation;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.service.EmailService;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PerformanceReportReminder {
    private final StudentService studentService;
    private final EmailService emailService;

    @Scheduled(cron = "0 0 0 1 1,7 *")
    public void sendReminderPerformanceReportEmail() {
        List<Student> studentToSendEmail = studentService.getStudentWithPerformanceReport();

        if (studentToSendEmail.isEmpty()) {
            return;
        }

        studentToSendEmail.forEach(student -> {
            try {
                int currentMonth = LocalDate.now().getMonthValue();
                YearMonth yearMonth = YearMonth.of(LocalDate.now().getYear(), currentMonth);
                LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

                this.emailService.sendEmail(
                        new EmailInformation(
                            student.getEmail(),
                            student.getStudentInformation().getName(),
                            lastDayOfMonth
                        )
                );
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
